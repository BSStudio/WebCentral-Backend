package hu.bme.sch.bss.webcentral.controller.videoportal;

import hu.bme.sch.bss.webcentral.videoportal.domain.VideoTagRequest;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoTagResponse;
import hu.bme.sch.bss.webcentral.videoportal.model.VideoTag;
import hu.bme.sch.bss.webcentral.videoportal.service.VideoTagService;
import hu.bme.sch.bss.webcentral.videoportal.service.VideoTypeService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;

@RestController
@RequestMapping(value = "/api/video/tag", produces = "application/json")
public class VideoTagController {
    private static final String REQUEST_VIDEO_TAG_CREATE = "Request for video tag creation received. {}";
    private static final String REQUEST_VIDEO_TAG_SEARCH = "Request to find video tag received for canonical name {}";

    private final VideoTagService videoTagService;
    private final Logger logger;

    VideoTagController(final VideoTagService videoTagService, final Logger logger) {
        this.videoTagService = videoTagService;
        this.logger = logger;
    }

    @PostMapping()
    @ResponseStatus(CREATED)
    public final VideoTagResponse createVideoTag(@Valid @RequestBody final VideoTagRequest request) {
        logger.info(REQUEST_VIDEO_TAG_CREATE, request);
        final VideoTag result = videoTagService.create(request);
        return new VideoTagResponse(result);
    }

    @GetMapping("/{canonicalName}")
    @ResponseStatus(FOUND)
    public final VideoTagResponse getVideoTag(@PathVariable("canonicalName") final String canonicalName) {
        logger.info(REQUEST_VIDEO_TAG_SEARCH, canonicalName);
        return new VideoTagResponse(videoTagService.findByCanonicalName(canonicalName));
    }

}

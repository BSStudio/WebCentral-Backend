package hu.bme.sch.bss.webcentral.controller.videoportal;

import hu.bme.sch.bss.webcentral.videoportal.domain.VideoTypeListResponse;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoTypeRequest;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoTypeResponse;
import hu.bme.sch.bss.webcentral.videoportal.model.VideoType;
import hu.bme.sch.bss.webcentral.videoportal.service.VideoTypeService;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

/**
 * @author Péter Veress
 */

@RestController
@RequestMapping(value = "/api/video/type", produces = "application/json")
public class VideoTypeController {
    private static final String REQUEST_VIDEO_TYPE_CREATE = "Request for video type creation received. {}";
    private static final String REQUEST_VIDEO_TYPE_SEARCH = "Request to find video type received for canonical name {}";

    private final VideoTypeService videoTypeService;
    private final Logger logger;

    VideoTypeController(final VideoTypeService videoTypeService, final Logger logger) {
        this.videoTypeService = videoTypeService;
        this.logger = logger;
    }

    @GetMapping("")
    @ResponseStatus(OK)
    public final VideoTypeListResponse getAllVideoType() {
        return VideoTypeListResponse.builder()
                .withVideoTypes(videoTypeService.findAll())
                .build();
    }

    @PostMapping()
    @ResponseStatus(CREATED)
    public final VideoTypeResponse createVideoType(@Valid @RequestBody final VideoTypeRequest request) {
        logger.info(REQUEST_VIDEO_TYPE_CREATE, request);
        final VideoType result = videoTypeService.create(request);
        return new VideoTypeResponse(result);
    }

    @GetMapping("/{canonicalName}")
    @ResponseStatus(FOUND)
    public final VideoTypeResponse getVideoType(@PathVariable("canonicalName") final String canonicalName) {
        logger.info(REQUEST_VIDEO_TYPE_SEARCH, canonicalName);
        return new VideoTypeResponse(videoTypeService.findByCanonicalName(canonicalName));
    }

}

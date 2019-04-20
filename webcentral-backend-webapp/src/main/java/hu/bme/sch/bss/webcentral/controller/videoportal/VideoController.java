package hu.bme.sch.bss.webcentral.controller.videoportal;

import hu.bme.sch.bss.webcentral.videoportal.domain.VideoListResponse;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoRequest;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoResponse;
import hu.bme.sch.bss.webcentral.videoportal.model.Video;
import hu.bme.sch.bss.webcentral.videoportal.model.VideoType;
import hu.bme.sch.bss.webcentral.videoportal.service.VideoService;
import hu.bme.sch.bss.webcentral.videoportal.service.VideoTypeService;

import java.util.ArrayList;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Péter Veress
 */

@RestController
@RequestMapping(value = "/api/video", produces = "application/json")
public class VideoController {
    private static final String REQUEST_VIDEO_CREATE = "Request for video creation received. {}";
    private static final String REQUEST_VIDEO_SEARCH = "Request to find video received for id {}";
    private static final String REQUEST_VIDEO_UPDATE = "Request to update video received for id {}";
    private static final String REQUEST_VIDEO_ARCHIVE = "Request to archive video received for id {}";
    private static final String REQUEST_VIDEO_RESTORE = "Request to restore video received for id {}";
    private static final String REQUEST_VIDEO_PUBLISH = "Request to publish video received for id {}";
    private static final String REQUEST_VIDEO_HIDE = "Request to hide video received for id {}";
    private static final String REQUEST_VIDEO_DELETE = "Request to delete video received for id {}";
    private static final String REQUEST_VIDEOS_LIST_ALL = "Request to list all video received.";
    private static final String REQUEST_VIDEOS_LIST_PUBLISHED = "Request to list published videos received.";
    private static final String REQUEST_VIDEOS_LIST_ARCHIVED = "Request to list archived videos received.";

    private final VideoService videoService;
    private final VideoTypeService videoTypeService;
    private final Logger logger;

    public VideoController(final VideoService videoService, final VideoTypeService videoTypeService, final Logger logger) {
        this.videoService = videoService;
        this.videoTypeService = videoTypeService;
        this.logger = logger;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public final VideoResponse createVideo(@Valid @RequestBody final VideoRequest request) {
        logger.info(REQUEST_VIDEO_CREATE, request);
        VideoType videoType = videoTypeService.findByCanonicalName(request.getVideoType());
        Video result = videoService.create(request, videoType);
        return new VideoResponse(result);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public final VideoResponse updateVideo(@PathVariable("id") final Long id, @RequestBody @Valid final VideoRequest request) {
        logger.info(REQUEST_VIDEO_UPDATE, id);
        Video video = videoService.findById(id);
        videoService.update(request, video);
        return new VideoResponse(video);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final VideoResponse getVideo(@PathVariable("id") final Long id) {
        logger.info(REQUEST_VIDEO_SEARCH, id);
        return new VideoResponse(videoService.findById(id));
    }

    @PutMapping("/{id}/archive")
    @ResponseStatus(HttpStatus.OK)
    public final void archiveVideo(@PathVariable("id") final Long id) {
        logger.info(REQUEST_VIDEO_ARCHIVE, id);
        Video video = videoService.findById(id);
        videoService.archive(video);
    }

    @PutMapping("/{id}/publish")
    @ResponseStatus(HttpStatus.OK)
    public final void publishVideo(@PathVariable("id") final Long id) {
        logger.info(REQUEST_VIDEO_PUBLISH, id);
        Video video = videoService.findById(id);
        videoService.publish(video);
    }

    @PutMapping("/{id}/hide")
    @ResponseStatus(HttpStatus.OK)
    public final void hideVideo(@PathVariable("id") final Long id) {
        logger.info(REQUEST_VIDEO_HIDE, id);
        Video video = videoService.findById(id);
        videoService.hide(video);
    }

    @PutMapping("/{id}/restore")
    @ResponseStatus(HttpStatus.OK)
    public final void restoreVideo(@PathVariable("id") final Long id) {
        logger.info(REQUEST_VIDEO_RESTORE, id);
        Video video = videoService.findById(id);
        videoService.restore(video);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public final void deleteVideo(@PathVariable("id") final Long id) {
        logger.info(REQUEST_VIDEO_DELETE, id);
        Video video = videoService.findById(id);
        videoService.delete(video);
    }

    @GetMapping("/published")
    @ResponseStatus(HttpStatus.OK)
    public final VideoListResponse listPublicVideos() {
        logger.info(REQUEST_VIDEOS_LIST_PUBLISHED);
        return VideoListResponse.builder()
            .withVideos(videoService.findPublished())
            .build();
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public final VideoListResponse listAllVideos() {
        logger.info(REQUEST_VIDEOS_LIST_ALL);
        return VideoListResponse.builder()
            .withVideos(new ArrayList<>(videoService.findAll()))
            .build();
    }

    @GetMapping("/archived")
    @ResponseStatus(HttpStatus.OK)
    public final VideoListResponse listAllArchived() {
        logger.info(REQUEST_VIDEOS_LIST_ARCHIVED);
        return VideoListResponse.builder()
            .withVideos(new ArrayList<>(videoService.findArchived()))
            .build();
    }

}

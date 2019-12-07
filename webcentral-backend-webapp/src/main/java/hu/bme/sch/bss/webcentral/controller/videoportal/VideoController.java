package hu.bme.sch.bss.webcentral.controller.videoportal;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.OK;

import hu.bme.sch.bss.webcentral.videoportal.domain.VideoListResponse;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoRequest;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoResponse;
import hu.bme.sch.bss.webcentral.videoportal.model.Video;
import hu.bme.sch.bss.webcentral.videoportal.model.VideoTag;
import hu.bme.sch.bss.webcentral.videoportal.model.VideoType;
import hu.bme.sch.bss.webcentral.videoportal.service.VideoService;
import hu.bme.sch.bss.webcentral.videoportal.service.VideoTagService;
import hu.bme.sch.bss.webcentral.videoportal.service.VideoTypeService;

import java.util.*;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

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
    private final VideoTagService videoTagService;
    private final Logger logger;

    public VideoController(VideoService videoService, VideoTypeService videoTypeService, VideoTagService videoTagService, Logger logger) {
        this.videoService = videoService;
        this.videoTypeService = videoTypeService;
        this.videoTagService = videoTagService;
        this.logger = logger;
    }

    @PostMapping()
    @ResponseStatus(CREATED)
    public final VideoResponse createVideo(@Valid @RequestBody final VideoRequest request) {
        logger.info(REQUEST_VIDEO_CREATE, request);
        final VideoType videoType = videoTypeService.findByCanonicalName(request.getVideoType());
        final Set<VideoTag> videoTags = new HashSet<>();
        if(request.getVideoTags() != null)
            request.getVideoTags().forEach((videoTag) -> videoTags.add(videoTagService.findByCanonicalName(videoTag)));
        final Video result = videoService.create(request, videoType, videoTags);
        return new VideoResponse(result);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public final VideoResponse updateVideo(@PathVariable("id") final Long id, @RequestBody @Valid final VideoRequest request) {
        logger.info(REQUEST_VIDEO_UPDATE, id);
        final Video video = videoService.findById(id);
        videoService.update(request, video);
        return new VideoResponse(video);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public final VideoResponse getVideo(@PathVariable("id") final Long id) {
        logger.info(REQUEST_VIDEO_SEARCH, id);
        return new VideoResponse(videoService.findById(id));
    }

    @PutMapping("/{id}/archive")
    @ResponseStatus(OK)
    public final void archiveVideo(@PathVariable("id") final Long id) {
        logger.info(REQUEST_VIDEO_ARCHIVE, id);
        final Video video = videoService.findById(id);
        videoService.archive(video);
    }

    @PutMapping("/{id}/publish")
    @ResponseStatus(OK)
    public final void publishVideo(@PathVariable("id") final Long id) {
        logger.info(REQUEST_VIDEO_PUBLISH, id);
        final Video video = videoService.findById(id);
        videoService.publish(video);
    }

    @PutMapping("/{id}/hide")
    @ResponseStatus(OK)
    public final void hideVideo(@PathVariable("id") final Long id) {
        logger.info(REQUEST_VIDEO_HIDE, id);
        final Video video = videoService.findById(id);
        videoService.hide(video);
    }

    @PutMapping("/{id}/restore")
    @ResponseStatus(OK)
    public final void restoreVideo(@PathVariable("id") final Long id) {
        logger.info(REQUEST_VIDEO_RESTORE, id);
        final Video video = videoService.findById(id);
        videoService.restore(video);
    }

    @PutMapping("/{id}/tag/{tagName}")
    @ResponseStatus(OK)
    public final void addTag(@PathVariable("id") final Long id, @PathVariable("tagName") final String tagName) {
        final Video video = videoService.findById(id);
        final VideoTag videoTag = videoTagService.findByCanonicalName(tagName);
        videoService.addTag(video, videoTag);
    }

    @GetMapping("/{id}/related")
    @ResponseStatus(OK)
    public final VideoListResponse addTag(@PathVariable("id") final Long id) {
        Video video = videoService.findById(id);
        return VideoListResponse.builder()
                .withVideos(videoService.findRelated(video))
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public final void deleteVideo(@PathVariable("id") final Long id) {
        logger.info(REQUEST_VIDEO_DELETE, id);
        final Video video = videoService.findById(id);
        videoService.delete(video);
    }

    @DeleteMapping("/{id}/tag/{tagName}")
    @ResponseStatus(OK)
    public final void removeTag(@PathVariable("id") final Long id, @PathVariable("tagName") final String tagName) {
        final Video video = videoService.findById(id);
        final VideoTag videoTag = videoTagService.findByCanonicalName(tagName);
        videoService.removeTag(video, videoTag);
    }

    @GetMapping("/published")
    @ResponseStatus(OK)
    public final VideoListResponse listPublicVideos() {
        logger.info(REQUEST_VIDEOS_LIST_PUBLISHED);
        return VideoListResponse.builder()
            .withVideos(videoService.findPublished())
            .build();
    }

    @GetMapping("/all")
    @ResponseStatus(OK)
    public final VideoListResponse listAllVideos(@RequestParam HashMap<String, String> searchParams) {
        logger.info(REQUEST_VIDEOS_LIST_ALL);
        List<Video> result;

        if(searchParams.size() == 0){
            result = videoService.findAll();
        } else {
            result = videoService.findByType(searchParams.get("type"));
        }

        return VideoListResponse.builder()
            .withVideos(new ArrayList<>(result))
            .build();
    }

    @GetMapping("/archived")
    @ResponseStatus(OK)
    public final VideoListResponse listAllArchived() {
        logger.info(REQUEST_VIDEOS_LIST_ARCHIVED);
        return VideoListResponse.builder()
            .withVideos(new ArrayList<>(videoService.findArchived()))
            .build();
    }

}

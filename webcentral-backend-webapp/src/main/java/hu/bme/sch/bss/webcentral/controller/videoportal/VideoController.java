package hu.bme.sch.bss.webcentral.controller.videoportal;

import hu.bme.sch.bss.webcentral.videoportal.VideoService;
import hu.bme.sch.bss.webcentral.videoportal.domain.CreateVideoRequest;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoListResponse;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoResponse;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import javax.validation.Valid;

import hu.bme.sch.bss.webcentral.videoportal.model.Video;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/video", produces = "application/json")
public class VideoController {

    private final VideoService videoService;

    public VideoController(final VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public final VideoResponse createVideo(
            @Valid @RequestBody final CreateVideoRequest request
    ) {
        return VideoResponse.builder()
                .withVideo(videoService.create(request))
                .build();
    }

    @GetMapping(value = "/published")
    @ResponseStatus(HttpStatus.OK)
    public final VideoListResponse listPublicVideos() {
        return VideoListResponse.builder()
                .withVideos(videoService.findPublished())
                .build();
    }

    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    public final VideoListResponse listAllVideos() {
        return VideoListResponse.builder()
                .withVideos(new ArrayList<>(videoService.findAll()))
                .build();
    }

    @GetMapping(value = "/archived")
    @ResponseStatus(HttpStatus.OK)
    public final VideoListResponse listAllArchived() {
        return VideoListResponse.builder()
                .withVideos(new ArrayList<>(videoService.findArchived()))
                .build();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final VideoResponse getVideo(@PathVariable("id") final Long id) {
        VideoResponse.Builder responseBuilder = VideoResponse.builder();
        try {
            responseBuilder.withVideo(videoService.findById(id));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Video Not Found", e);
        }
        return responseBuilder.build();
    }

    @PutMapping(value = "/{id}/archive")
    @ResponseStatus(HttpStatus.OK)
    public final void archiveVideo(@PathVariable("id") final Long id) {
        Video video = videoService.findById(id);
        videoService.archive(video);
    }

}

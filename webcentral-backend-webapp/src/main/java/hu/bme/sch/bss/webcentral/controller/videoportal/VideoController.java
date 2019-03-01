package hu.bme.sch.bss.webcentral.controller.videoportal;

import hu.bme.sch.bss.webcentral.VideoService;
import hu.bme.sch.bss.webcentral.domain.CreateVideoRequest;
import hu.bme.sch.bss.webcentral.domain.CreateVideoResponse;
import hu.bme.sch.bss.webcentral.domain.ListVideosResponse;
import hu.bme.sch.bss.webcentral.model.Video;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/video", produces = "application/json")
public class VideoController {

    private final VideoService videoService;

    public VideoController(final VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping()
    public final CreateVideoResponse createVideo(
            @Valid @RequestBody final CreateVideoRequest request
    ) {
        Video result = videoService.create(request);
        return CreateVideoResponse.builder()
                .withVideo(result)
                .build();
    }

    @GetMapping(value = "/published")
    public final ListVideosResponse listPublicVideos() {
        List<Video> videoList = videoService.findPublished();
        return ListVideosResponse.builder()
                .withVideos(videoList)
                .build();
    }

    @GetMapping(value = "/all")
    public final ListVideosResponse listAllVideos() {
        ArrayList<Video> videoList = new ArrayList<>(videoService.findAll());
        return ListVideosResponse.builder()
                .withVideos(videoList)
                .build();
    }

}

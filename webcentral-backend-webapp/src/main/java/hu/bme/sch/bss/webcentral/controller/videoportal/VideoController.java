package hu.bme.sch.bss.webcentral.controller.videoportal;

import hu.bme.sch.bss.webcentral.VideoService;
import hu.bme.sch.bss.webcentral.domain.CreateVideoRequest;
import hu.bme.sch.bss.webcentral.domain.CreateVideoResponse;
import hu.bme.sch.bss.webcentral.domain.ListVideosResponse;

import java.util.ArrayList;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public final ResponseEntity createVideo(
            @Valid @RequestBody final CreateVideoRequest request
    ) {
        CreateVideoResponse result = CreateVideoResponse.builder()
                .withVideo(videoService.create(request))
                .build();
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @GetMapping(value = "/published")
    public final ResponseEntity listPublicVideos() {
        ListVideosResponse result = ListVideosResponse.builder()
                .withVideos(videoService.findPublished())
                .build();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public final ResponseEntity listAllVideos() {
        ListVideosResponse result = ListVideosResponse.builder()
                .withVideos(new ArrayList<>(videoService.findAll()))
                .build();
        return new ResponseEntity(result, HttpStatus.OK);
    }

}

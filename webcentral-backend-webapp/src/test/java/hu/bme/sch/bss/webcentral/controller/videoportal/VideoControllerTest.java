package hu.bme.sch.bss.webcentral.controller.videoportal;

import hu.bme.sch.bss.webcentral.VideoService;
import hu.bme.sch.bss.webcentral.domain.CreateVideoRequest;
import hu.bme.sch.bss.webcentral.domain.CreateVideoResponse;
import hu.bme.sch.bss.webcentral.domain.ListVideosResponse;
import hu.bme.sch.bss.webcentral.model.Video;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VideoControllerTest {

    private static final String LONG_NAME = "long name";
    private static final String CANONICAL_NAME = "canonical-name";
    private static final String PROJECT_NAME = "projectName";
    private static final String DESCRIPTION = "description";
    private static final Boolean VISIBILITY = true;
    private static final String IMAGE_LOCATION = "image/location";
    private static final String VIDEO_LOCATION = "video/location";

    @Mock
    private VideoService mockVideoService;
    private VideoController underTest;
    private Video video;

    @BeforeEach
    public void init(){
        initMocks(this);
        underTest = new VideoController(mockVideoService);
        video = Video.builder()
                .withLongName(LONG_NAME)
                .withCanonicalName(CANONICAL_NAME)
                .withDescription(DESCRIPTION)
                .withProjectName(PROJECT_NAME)
                .withVisible(VISIBILITY)
                .withVideoLocation(VIDEO_LOCATION)
                .withImageLocation(IMAGE_LOCATION)
                .build();
    }


    @Test
    public void testCreateVideo() {
        // GIVEN
        CreateVideoRequest request = CreateVideoRequest.builder()
                .withLongName(LONG_NAME)
                .withCanonicalName(CANONICAL_NAME)
                .withDescription(DESCRIPTION)
                .withProjectName(PROJECT_NAME)
                .withVisible(VISIBILITY)
                .withVideoLocation(VIDEO_LOCATION)
                .withImageLocation(IMAGE_LOCATION)
                .build();
        given(mockVideoService.create(request)).willReturn(video);

        // WHEN
        ResponseEntity response = underTest.createVideo(request);

        // THEN
        assertEquals(video, ((CreateVideoResponse)response.getBody()).getVideo());
    }

    @Test
    public void TestListPublicVideos(){
        // GIVEN
        List<Video> videoList = new ArrayList<>();

        Video video2 = Video.builder()
                .build();

        videoList.add(video);
        videoList.add(video2);

        given(mockVideoService.findPublished()).willReturn(videoList);

        // WHEN
        ResponseEntity response = underTest.listPublicVideos();

        // THEN
        assertEquals(videoList, Arrays.asList(((ListVideosResponse) response.getBody()).getVideos()));
    }

    @Test
    public void TestListAllVideos(){
        // GIVEN
        List<Video> videoList = new ArrayList<>();

        Video video2 = Video.builder()
                .build();

        videoList.add(video);
        videoList.add(video2);

        given(mockVideoService.findAll()).willReturn(videoList);

        // WHEN
        ResponseEntity response = underTest.listAllVideos();

        // THEN
        assertEquals(videoList, Arrays.asList(((ListVideosResponse) response.getBody()).getVideos()));
    }
}

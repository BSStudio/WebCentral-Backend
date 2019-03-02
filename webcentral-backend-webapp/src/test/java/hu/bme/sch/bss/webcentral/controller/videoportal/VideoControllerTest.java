package hu.bme.sch.bss.webcentral.controller.videoportal;

import hu.bme.sch.bss.webcentral.VideoService;
import hu.bme.sch.bss.webcentral.domain.CreateVideoRequest;
import hu.bme.sch.bss.webcentral.domain.VideoListResponse;
import hu.bme.sch.bss.webcentral.domain.VideoResponse;
import hu.bme.sch.bss.webcentral.model.Video;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

class VideoControllerTest {

    private static final String LONG_NAME = "long name";
    private static final String CANONICAL_NAME = "canonical-name";
    private static final String PROJECT_NAME = "projectName";
    private static final String DESCRIPTION = "description";
    private static final Boolean VISIBILITY = true;
    private static final String IMAGE_LOCATION = "image/location";
    private static final String VIDEO_LOCATION = "video/location";
    public static final long VIDEO_ID = 16L;

    @Mock
    private VideoService mockVideoService;
    private VideoController underTest;
    private Video video;

    @BeforeEach
    void init() {
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
    void testCreateVideo() {
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
        VideoResponse response = underTest.createVideo(request);

        // THEN
        assertEquals(video, response.getVideo());
    }

    @Test
    void testListPublicVideos() {
        // GIVEN
        List<Video> videoList = new ArrayList<>();

        Video video2 = Video.builder()
                .build();

        videoList.add(video);
        videoList.add(video2);

        given(mockVideoService.findPublished()).willReturn(videoList);

        // WHEN
        VideoListResponse response = underTest.listPublicVideos();

        // THEN
        assertEquals(videoList, Arrays.asList(response.getVideos()));
    }

    @Test
    void testGetVideo() {
        // GIVEN
        Video video = Video.builder()
                .build();

        given(mockVideoService.findById(VIDEO_ID)).willReturn(video);

        // WHEN
        VideoResponse response = underTest.getVideo(VIDEO_ID);

        // THEN
        assertEquals(video, response.getVideo());
    }

    @Test
    void testGetVideoShouldThrowNoSuchElementExceptionWhenWrongId() {
        // GIVEN
        given(mockVideoService.findById(VIDEO_ID)).willThrow(new NoSuchElementException());

        // WHEN
        ResponseStatusException exception = null;
        try {
            underTest.getVideo(VIDEO_ID);
        } catch (ResponseStatusException e) {
            exception = e;
        }

        // THEN
        assertNotNull(exception);
        assertEquals("404 NOT_FOUND \"Video Not Found\"; nested exception is java.util.NoSuchElementException", exception.getMessage());
    }

    @Test
    void testListAllVideos() {
        // GIVEN
        List<Video> videoList = new ArrayList<>();

        Video video2 = Video.builder()
                .build();

        videoList.add(video);
        videoList.add(video2);

        given(mockVideoService.findAll()).willReturn(videoList);

        // WHEN
        VideoListResponse response = underTest.listAllVideos();

        // THEN
        assertEquals(videoList, Arrays.asList(response.getVideos()));
    }
}

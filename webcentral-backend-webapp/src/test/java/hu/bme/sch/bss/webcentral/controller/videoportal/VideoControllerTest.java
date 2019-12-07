package hu.bme.sch.bss.webcentral.controller.videoportal;

import hu.bme.sch.bss.webcentral.videoportal.model.VideoType;
import hu.bme.sch.bss.webcentral.videoportal.service.VideoService;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoRequest;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoListResponse;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoResponse;
import hu.bme.sch.bss.webcentral.videoportal.model.Video;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import hu.bme.sch.bss.webcentral.videoportal.service.VideoTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

class VideoControllerTest {
    private static final String LONG_NAME = "long name";
    private static final String CANONICAL_NAME = "canonical-name";
    private static final String PROJECT_NAME = "projectName";
    private static final String DESCRIPTION = "description";
    private static final Boolean VISIBLE = true;
    private static final String IMAGE_LOCATION = "image/location";
    private static final String VIDEO_LOCATION = "video/location";
    private static final long VIDEO_ID = 16L;
    private static final String VIDEO_TYPE_CANONICAL_NAME = "video-type";

    private VideoController underTest;
    private Video video;

    @Mock
    private VideoService mockVideoService;
    @Mock
    private VideoTypeService mockVideoTypeService;
    @Mock
    private Logger mockLogger;
    @Mock
    private VideoType mockVideoType;

    @BeforeEach
    void init() {
        initMocks(this);
        underTest = new VideoController(mockVideoService, mockVideoTypeService, null, mockLogger);
        video = Video.builder()
            .withLongName(LONG_NAME)
            .withCanonicalName(CANONICAL_NAME)
            .withDescription(DESCRIPTION)
            .withProjectName(PROJECT_NAME)
            .withVideoType(mockVideoType)
            .withVisible(VISIBLE)
            .withVideoLocation(VIDEO_LOCATION)
            .withImageLocation(IMAGE_LOCATION)
            .build();
        given(mockVideoType.getCanonicalName()).willReturn(VIDEO_TYPE_CANONICAL_NAME);
        given(mockVideoTypeService.findByCanonicalName(VIDEO_TYPE_CANONICAL_NAME)).willReturn(mockVideoType);
    }

    @Test
    void testCreateVideo() {
        // GIVEN
        VideoRequest request = VideoRequest.builder()
            .withLongName(LONG_NAME)
            .withCanonicalName(CANONICAL_NAME)
            .withDescription(DESCRIPTION)
            .withProjectName(PROJECT_NAME)
            .withVisible(VISIBLE)
            .withVideoLocation(VIDEO_LOCATION)
            .withImageLocation(IMAGE_LOCATION)
            .withVideoType(VIDEO_TYPE_CANONICAL_NAME)
            .build();

        given(mockVideoService.create(request, mockVideoType, null)).willReturn(video);

        // WHEN
        VideoResponse response = underTest.createVideo(request);

        // THEN
        assertAll(
            () -> assertEquals(request.getLongName(), response.getLongName()),
            () -> assertEquals(request.getProjectName(), response.getProjectName()),
            () -> assertEquals(request.getCanonicalName(), response.getCanonicalName()),
            () -> assertEquals(request.getDescription(), response.getDescription()),
            () -> assertEquals(request.getVisible(), response.getVisible()),
            () -> assertEquals(request.getImageLocation(), response.getImageLocation()),
            () -> assertEquals(request.getVideoLocation(), response.getVideoLocation()),
            () -> assertEquals(VIDEO_TYPE_CANONICAL_NAME, response.getVideoType())
        );
    }

    @Test
    void testListPublicVideos() {
        // GIVEN
        List<Video> videoList = new ArrayList<>();
        List<VideoResponse> responseList = new ArrayList<>();

        Video video2 = Video.builder()
            .withVideoType(mockVideoType)
            .build();
      
        videoList.add(video);
        videoList.add(video2);

        responseList.add(new VideoResponse(video));
        responseList.add(new VideoResponse(video2));

        given(mockVideoService.findPublished()).willReturn(videoList);

        // WHEN
        VideoListResponse response = underTest.listPublicVideos();

        // THEN
        assertEquals(responseList, Arrays.asList(response.getVideos()));
    }

    @Test
    void testGetVideo() {
        // GIVEN
        Video actual = Video.builder()
            .withLongName(LONG_NAME)
            .withCanonicalName(CANONICAL_NAME)
            .withDescription(DESCRIPTION)
            .withProjectName(PROJECT_NAME)
            .withVisible(VISIBLE)
            .withVideoLocation(VIDEO_LOCATION)
            .withImageLocation(IMAGE_LOCATION)
            .build();

        given(mockVideoService.findById(VIDEO_ID)).willReturn(video);

        // WHEN
        VideoResponse response = underTest.getVideo(VIDEO_ID);

        // THEN
        assertAll(
            () -> assertEquals(actual.getLongName(), response.getLongName()),
            () -> assertEquals(actual.getProjectName(), response.getProjectName()),
            () -> assertEquals(actual.getCanonicalName(), response.getCanonicalName()),
            () -> assertEquals(actual.getDescription(), response.getDescription()),
            () -> assertEquals(actual.getVisible(), response.getVisible()),
            () -> assertEquals(actual.getImageLocation(), response.getImageLocation()),
            () -> assertEquals(actual.getVideoLocation(), response.getVideoLocation())
        );
    }

    @Test
    void testGetVideoShouldThrowNoSuchElementExceptionWhenWrongId() {
        // GIVEN
        given(mockVideoService.findById(VIDEO_ID)).willThrow(new NoSuchElementException());

        // WHEN
        Exception exception = null;
        VideoResponse response = null;
        try {
            response = underTest.getVideo(VIDEO_ID);
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertNotNull(exception);
        assertNull(response);
        verify(mockVideoService).findById(VIDEO_ID);
    }

    @Test
    void testListAllVideos() {
        // GIVEN
        List<Video> videoList = new ArrayList<>();
        List<VideoResponse> responseList = new ArrayList<>();

        Video video2 = Video.builder()
            .withVideoType(mockVideoType)
            .build();

        videoList.add(video);
        videoList.add(video2);

        responseList.add(new VideoResponse(video));
        responseList.add(new VideoResponse(video2));

        given(mockVideoService.findAll()).willReturn(videoList);

        // WHEN
        VideoListResponse response = underTest.listAllVideos(null);

        // THEN
        assertEquals(responseList, Arrays.asList(response.getVideos()));
    }

    @Test
    void testListArchivedVideos() {
        // GIVEN
        List<Video> archivedList = new ArrayList<>();
        List<VideoResponse> responseList = new ArrayList<>();

        Video video2 = Video.builder()
            .withVideoType(mockVideoType)
            .build();

        archivedList.add(video);
        archivedList.add(video2);

        responseList.add(new VideoResponse(video));
        responseList.add(new VideoResponse(video2));

        given(mockVideoService.findArchived()).willReturn(archivedList);

        // WHEN
        VideoListResponse response = underTest.listAllArchived();

        // THEN
        assertEquals(responseList, Arrays.asList(response.getVideos()));
    }

    @Test
    void testArchiveVideo() {
        // GIVEN
        given(mockVideoService.findById(VIDEO_ID)).willReturn(video);

        // WHEN
        underTest.archiveVideo(VIDEO_ID);

        // THEN
        then(mockVideoService).should().findById(VIDEO_ID);
        then(mockVideoService).should().archive(video);
    }

    @Test
    void testRestoreVideo() {
        // GIVEN
        given(mockVideoService.findById(VIDEO_ID)).willReturn(video);

        // WHEN
        underTest.restoreVideo(VIDEO_ID);

        // THEN
        then(mockVideoService).should().findById(VIDEO_ID);
        then(mockVideoService).should().restore(video);
    }

    @Test
    void testDeleteVideo() {
        // GIVEN
        given(mockVideoService.findById(VIDEO_ID)).willReturn(video);

        // WHEN
        underTest.deleteVideo(VIDEO_ID);

        // THEN
        then(mockVideoService).should().findById(VIDEO_ID);
        then(mockVideoService).should().delete(video);
    }

    @Test
    void testUpdateVideo() {
        // GIVEN
        given(mockVideoService.findById(VIDEO_ID)).willReturn(video);
        VideoRequest request = VideoRequest.builder()
            .withLongName(LONG_NAME)
            .withCanonicalName(CANONICAL_NAME)
            .withDescription(DESCRIPTION)
            .withProjectName(PROJECT_NAME)
            .withVisible(VISIBLE)
            .withVideoLocation(VIDEO_LOCATION)
            .withImageLocation(IMAGE_LOCATION)
            .build();

        // WHEN
        VideoResponse response = underTest.updateVideo(VIDEO_ID, request);

        // THEN
        then(mockVideoService).should().findById(VIDEO_ID);
        then(mockVideoService).should().update(request, video);
        assertAll(
            () -> assertEquals(request.getLongName(), response.getLongName()),
            () -> assertEquals(request.getProjectName(), response.getProjectName()),
            () -> assertEquals(request.getCanonicalName(), response.getCanonicalName()),
            () -> assertEquals(request.getDescription(), response.getDescription()),
            () -> assertEquals(request.getVisible(), response.getVisible()),
            () -> assertEquals(request.getImageLocation(), response.getImageLocation()),
            () -> assertEquals(request.getVideoLocation(), response.getVideoLocation())
        );
    }

    @Test
    void testPublishVideo() {
        // GIVEN
        video.setVisible(false);
        given(mockVideoService.findById(VIDEO_ID)).willReturn(video);

        // WHEN
        underTest.publishVideo(VIDEO_ID);

        // THEN
        then(mockVideoService).should().findById(VIDEO_ID);
        then(mockVideoService).should().publish(video);
    }

    @Test
    void testHideVideo() {
        // GIVEN
        video.setVisible(true);
        given(mockVideoService.findById(VIDEO_ID)).willReturn(video);


        // WHEN
        underTest.hideVideo(VIDEO_ID);

        // THEN
        then(mockVideoService).should().findById(VIDEO_ID);
        then(mockVideoService).should().hide(video);
    }
}

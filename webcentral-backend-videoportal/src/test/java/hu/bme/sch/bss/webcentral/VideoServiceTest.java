package hu.bme.sch.bss.webcentral;

import hu.bme.sch.bss.webcentral.dao.VideoDao;
import hu.bme.sch.bss.webcentral.domain.CreateVideoRequest;
import hu.bme.sch.bss.webcentral.model.Video;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

class VideoServiceTest {

    private static final String LONG_NAME = "long name";
    private static final String CANONICAL_NAME = "canonical-name";
    private static final String PROJECT_NAME = "projectName";
    private static final String DESCRIPTION = "description";
    private static final boolean VISIBILITY = true;
    private static final String IMAGE_LOCATION = "image/location";
    private static final String VIDEO_LOCATION = "video/location";

    @Mock
    private CreateVideoRequest mockCreateVideoRequest;
    @Mock
    private Logger mockLogger;
    @Mock
    private VideoDao mockVideoDao;

    // Spy
    private VideoService underTest;

    private Video video;

    @BeforeEach
    void init() {
        initMocks(this);
        underTest = spy(new VideoService(mockVideoDao, mockLogger));

        given(mockCreateVideoRequest.getLongName()).willReturn(LONG_NAME);
        given(mockCreateVideoRequest.getCanonicalName()).willReturn(CANONICAL_NAME);
        given(mockCreateVideoRequest.getProjectName()).willReturn(PROJECT_NAME);
        given(mockCreateVideoRequest.getDescription()).willReturn(DESCRIPTION);
        given(mockCreateVideoRequest.getVisible()).willReturn(VISIBILITY);
        given(mockCreateVideoRequest.getVideoLocation()).willReturn(VIDEO_LOCATION);
        given(mockCreateVideoRequest.getImageLocation()).willReturn(IMAGE_LOCATION);

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
        doReturn(video).when(underTest).createVideoWithRequestData(any());

        // WHEN
        Video result = underTest.create(mockCreateVideoRequest);

        // THEN
        then(underTest).should().createVideoWithRequestData(mockCreateVideoRequest);
        then(mockVideoDao).should().save(video);

        assertEquals(video, result);
    }

    @Test
    void testCreateVideoWithRequestData() {
        // GIVEN setup

        // WHEN
        Video result = underTest.createVideoWithRequestData(mockCreateVideoRequest);

        // THEN
        then(mockCreateVideoRequest).should().getLongName();
        then(mockCreateVideoRequest).should().getCanonicalName();
        then(mockCreateVideoRequest).should().getProjectName();
        then(mockCreateVideoRequest).should().getDescription();
        then(mockCreateVideoRequest).should().getVisible();
        then(mockCreateVideoRequest).should().getVideoLocation();
        then(mockCreateVideoRequest).should().getImageLocation();

        assertEquals(LONG_NAME, result.getLongName());
        assertEquals(CANONICAL_NAME, result.getCanonicalName());
        assertEquals(PROJECT_NAME, result.getProjectName());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(VISIBILITY, result.getVisible());
        assertEquals(VIDEO_LOCATION, result.getVideoLocation());
        assertEquals(IMAGE_LOCATION, result.getImageLocation());
    }

    @Test
    void testFindPublished() {
        // GIVEN setup
        List<Video> videoList = new ArrayList<>();

        Video video2 = Video.builder()
                .build();

        videoList.add(video);
        videoList.add(video2);

        given(mockVideoDao.findAllPublished()).willReturn(videoList);

        // WHEN
        List<Video> result = underTest.findPublished();

        // THEN
        assertEquals(videoList, result);
    }

    @Test
    void testFindAll() {
        // GIVEN setup
        List<Video> videoList = new ArrayList<>();

        Video video2 = Video.builder()
                .build();

        videoList.add(video);
        videoList.add(video2);

        given(mockVideoDao.findAll()).willReturn(videoList);

        // WHEN
        List<Video> result = underTest.findAll();

        // THEN
        assertEquals(videoList, result);
    }
}

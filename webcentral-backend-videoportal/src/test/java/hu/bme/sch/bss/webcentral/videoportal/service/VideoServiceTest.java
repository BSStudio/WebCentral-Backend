package hu.bme.sch.bss.webcentral.videoportal.service;

import hu.bme.sch.bss.webcentral.videoportal.dao.VideoDao;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoRequest;
import hu.bme.sch.bss.webcentral.videoportal.model.Video;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.initMocks;

import hu.bme.sch.bss.webcentral.videoportal.model.VideoType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

class VideoServiceTest {
    private static final long VIDEO_ID = 16L;

    private static final String LONG_NAME = "long name";
    private static final String CANONICAL_NAME = "canonical-name";
    private static final String PROJECT_NAME = "projectName";
    private static final String DESCRIPTION = "description";
    private static final boolean VISIBILITY = true;
    private static final String IMAGE_LOCATION = "image/location";
    private static final String VIDEO_LOCATION = "video/location";

    @Mock
    private VideoRequest mockVideoRequest;
    @Mock
    private Logger mockLogger;
    @Mock
    private VideoDao mockVideoDao;
    @Mock
    private VideoType mockVideoType;

    private Video video;
    private VideoService underTest;


    @BeforeEach
    void init() {
        initMocks(this);
        underTest = new VideoService(mockVideoDao, mockLogger);

        given(mockVideoRequest.getLongName()).willReturn(LONG_NAME);
        given(mockVideoRequest.getCanonicalName()).willReturn(CANONICAL_NAME);
        given(mockVideoRequest.getProjectName()).willReturn(PROJECT_NAME);
        given(mockVideoRequest.getDescription()).willReturn(DESCRIPTION);
        given(mockVideoRequest.getVisible()).willReturn(VISIBILITY);
        given(mockVideoRequest.getVideoLocation()).willReturn(VIDEO_LOCATION);
        given(mockVideoRequest.getImageLocation()).willReturn(IMAGE_LOCATION);

        video = Video.builder()
            .withLongName(LONG_NAME)
            .withCanonicalName(CANONICAL_NAME)
            .withDescription(DESCRIPTION)
            .withProjectName(PROJECT_NAME)
            .withVideoType(mockVideoType)
            .withVisible(VISIBILITY)
            .withVideoLocation(VIDEO_LOCATION)
            .withImageLocation(IMAGE_LOCATION)
            .build();
    }

    @Test
    void testCreateVideo() {
        // GIVEN

        // WHEN
        Video result = underTest.create(mockVideoRequest, mockVideoType);

        // THEN
        then(mockVideoDao).should().save(video);

        then(mockVideoRequest).should().getLongName();
        then(mockVideoRequest).should().getCanonicalName();
        then(mockVideoRequest).should().getProjectName();
        then(mockVideoRequest).should().getDescription();
        then(mockVideoRequest).should().getVisible();
        then(mockVideoRequest).should().getVideoLocation();
        then(mockVideoRequest).should().getImageLocation();

        assertAll(
            () -> assertEquals(LONG_NAME, result.getLongName()),
            () -> assertEquals(CANONICAL_NAME, result.getCanonicalName()),
            () -> assertEquals(PROJECT_NAME, result.getProjectName()),
            () -> assertEquals(DESCRIPTION, result.getDescription()),
            () -> assertEquals(VISIBILITY, result.getVisible()),
            () -> assertEquals(VIDEO_LOCATION, result.getVideoLocation()),
            () -> assertEquals(IMAGE_LOCATION, result.getImageLocation()),
            () -> assertEquals(mockVideoType, result.getVideoType())
        );
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

    @Test
    void testFindById() {
        // GIVEN setup
        Video video = Video.builder()
            .build();

        given(mockVideoDao.findById(VIDEO_ID)).willReturn(Optional.of(video));

        // WHEN
        Video result = underTest.findById(VIDEO_ID);

        // THEN
        assertEquals(video, result);
    }

    @Test
    void testFindByIdShouldThrowNoSuchElementExceptionWhenWrongId() {
        // GIVEN setup
        given(mockVideoDao.findById(any())).willReturn(Optional.empty());

        // WHEN
        NoSuchElementException exception = null;
        try {
            underTest.findById(VIDEO_ID);
        } catch (NoSuchElementException e) {
            exception = e;
        }

        // THEN
        assertNotNull(exception);
    }

    @Test
    void testFindArchived() {
        // GIVEN setup
        List<Video> archivedList = new ArrayList<>();

        Video video2 = Video.builder()
            .build();

        archivedList.add(video);
        archivedList.add(video2);

        given(mockVideoDao.findAllArchived()).willReturn(archivedList);

        // WHEN
        List<Video> result = underTest.findArchived();

        // THEN
        assertEquals(archivedList, result);
    }

    @Test
    void testArchiving() {
        // GIVEN setup

        // WHEN
        underTest.archive(video);

        // THEN
        assertTrue(video.getArchived());
        then(mockVideoDao).should().save(video);
    }

    @Test
    void testRestore() {
        // GIVEN setup

        // WHEN
        underTest.restore(video);

        // THEN
        assertFalse(video.getArchived());
        then(mockVideoDao).should().save(video);
    }

    @Test
    void testDelete() {
        // GIVEN setup

        // WHEN
        underTest.delete(video);

        // THEN
        then(mockVideoDao).should().delete(video);
    }

    @Test
    void testUpdate() {
        // GIVEN

        // WHEN
        underTest.update(mockVideoRequest, video);

        // THEN
        then(mockVideoRequest).should().getLongName();
        then(mockVideoRequest).should().getCanonicalName();
        then(mockVideoRequest).should().getProjectName();
        then(mockVideoRequest).should().getDescription();
        then(mockVideoRequest).should().getVisible();
        then(mockVideoRequest).should().getVideoLocation();
        then(mockVideoRequest).should().getImageLocation();

        assertAll(
            () -> assertEquals(LONG_NAME, video.getLongName()),
            () -> assertEquals(CANONICAL_NAME, video.getCanonicalName()),
            () -> assertEquals(PROJECT_NAME, video.getProjectName()),
            () -> assertEquals(DESCRIPTION, video.getDescription()),
            () -> assertEquals(VISIBILITY, video.getVisible()),
            () -> assertEquals(VIDEO_LOCATION, video.getVideoLocation()),
            () -> assertEquals(IMAGE_LOCATION, video.getImageLocation())
        );

        then(mockVideoDao).should().save(video);
    }

    @Test
    void testPublish() {
        // GIVEN
        video.setVisible(false);

        // WHEN
        underTest.publish(video);

        // THEN
        assertTrue(video.getVisible());
        then(mockVideoDao).should().save(video);
    }

    @Test
    void testHide() {
        // GIVEN
        video.setVisible(true);

        // WHEN
        underTest.hide(video);

        // THEN
        assertFalse(video.getVisible());
        then(mockVideoDao).should().save(video);
    }

}

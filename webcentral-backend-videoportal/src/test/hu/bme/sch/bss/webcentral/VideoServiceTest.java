package hu.bme.sch.bss.webcentral;

import hu.bme.sch.bss.webcentral.dao.VideoDao;
import hu.bme.sch.bss.webcentral.domain.CreateVideoRequest;
import hu.bme.sch.bss.webcentral.model.Video;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;

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

    private VideoService underTest;

    @BeforeEach
    void init() {
        initMocks(this);
        underTest = new VideoService(mockVideoDao, mockLogger);

        given(mockCreateVideoRequest.getLongName()).willReturn(LONG_NAME);
        given(mockCreateVideoRequest.getCanonicalName()).willReturn(CANONICAL_NAME);
        given(mockCreateVideoRequest.getProjectName()).willReturn(PROJECT_NAME);
        given(mockCreateVideoRequest.getDescription()).willReturn(DESCRIPTION);
        given(mockCreateVideoRequest.getVisible()).willReturn(VISIBILITY);
        given(mockCreateVideoRequest.getVideoLocation()).willReturn(VIDEO_LOCATION);
        given(mockCreateVideoRequest.getImageLocation()).willReturn(IMAGE_LOCATION);
    }

    @Test
    void testCreateVideo() {
        // GIVEN setup

        // WHEN
        Video result = underTest.createVideo(mockCreateVideoRequest);

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

}

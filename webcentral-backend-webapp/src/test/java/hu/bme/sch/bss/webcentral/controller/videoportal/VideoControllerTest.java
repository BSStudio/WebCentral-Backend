package hu.bme.sch.bss.webcentral.controller.videoportal;

import hu.bme.sch.bss.webcentral.VideoService;
import hu.bme.sch.bss.webcentral.domain.CreateVideoRequest;
import hu.bme.sch.bss.webcentral.model.Video;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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

    @BeforeEach
    public void init(){
        initMocks(this);
        underTest = new VideoController(mockVideoService);
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
        Video expectedResult = Video.builder()
                .withLongName(LONG_NAME)
                .withCanonicalName(CANONICAL_NAME)
                .withDescription(DESCRIPTION)
                .withProjectName(PROJECT_NAME)
                .withVisible(VISIBILITY)
                .withVideoLocation(VIDEO_LOCATION)
                .withImageLocation(IMAGE_LOCATION)
                .build();
        given(mockVideoService.createVideo(request)).willReturn(expectedResult);

        // WHEN
        Video actualResult = underTest.createVideo(request);

        // THEN
        assertEquals(expectedResult, actualResult);
    }
}

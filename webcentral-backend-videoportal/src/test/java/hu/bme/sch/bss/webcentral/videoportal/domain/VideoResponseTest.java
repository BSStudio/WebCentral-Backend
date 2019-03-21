package hu.bme.sch.bss.webcentral.videoportal.domain;

import hu.bme.sch.bss.webcentral.videoportal.model.Video;
import hu.bme.sch.bss.webcentral.videoportal.model.VideoType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class VideoResponseTest {
    private static final String LONG_NAME = "long name";
    private static final String CANONICAL_NAME = "canonical-name";
    private static final String PROJECT_NAME = "projectName";
    private static final String DESCRIPTION = "description";
    private static final Boolean VISIBLE = true;
    private static final String IMAGE_LOCATION = "image/location";
    private static final String VIDEO_LOCATION = "video/location";
    public static final String VIDEO_TYPE_NAME = "type-name";

    @Test
    void testConstructor() {
        // GIVEN
        VideoType mockType = mock(VideoType.class);
        given(mockType.getCanonicalName()).willReturn(VIDEO_TYPE_NAME);

        Video actual = Video.builder()
            .withLongName(LONG_NAME)
            .withCanonicalName(CANONICAL_NAME)
            .withDescription(DESCRIPTION)
            .withProjectName(PROJECT_NAME)
            .withVideoType(mockType)
            .withVisible(VISIBLE)
            .withVideoLocation(VIDEO_LOCATION)
            .withImageLocation(IMAGE_LOCATION)
            .build();

        // WHEN
        VideoResponse result = new VideoResponse(actual);

        // THEN
        assertAll(
            () -> assertEquals(actual.getId(), result.getId()),
            () -> assertEquals(actual.getLongName(), result.getLongName()),
            () -> assertEquals(actual.getProjectName(), result.getProjectName()),
            () -> assertEquals(actual.getCanonicalName(), result.getCanonicalName()),
            () -> assertEquals(actual.getDescription(), result.getDescription()),
            () -> assertEquals(VIDEO_TYPE_NAME, result.getVideoType()),
            () -> assertEquals(actual.getVisible(), result.getVisible()),
            () -> assertEquals(actual.getImageLocation(), result.getImageLocation()),
            () -> assertEquals(actual.getVideoLocation(), result.getVideoLocation()),
            () -> assertFalse(result.getArchived())
        );
    }

}

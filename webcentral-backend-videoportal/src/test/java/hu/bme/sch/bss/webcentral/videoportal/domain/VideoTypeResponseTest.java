package hu.bme.sch.bss.webcentral.videoportal.domain;

import hu.bme.sch.bss.webcentral.videoportal.model.VideoType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @author Péter Veress
 */

class VideoTypeResponseTest {

    private static final String CANONICAL_NAME = "canonical-name";
    private static final String LONG_NAME = "long name";
    private static final String DESCRIPTION = "description";

    VideoTypeResponse underTest;

    @Test
    void testConstructorAndBuilder() {
        // GIVEN
        VideoType mockType = mock(VideoType.class);
        given(mockType.getCanonicalName()).willReturn(CANONICAL_NAME);
        given(mockType.getLongName()).willReturn(LONG_NAME);
        given(mockType.getDescription()).willReturn(DESCRIPTION);

        // WHEN
        underTest = new VideoTypeResponse(mockType);

        // THEN
        assertAll(
            () -> assertEquals(CANONICAL_NAME, underTest.getCanonicalName()),
            () -> assertEquals(LONG_NAME, underTest.getLongName()),
            () -> assertEquals(DESCRIPTION, underTest.getDescription()),
            () -> assertFalse(underTest.getArchived())
        );
    }

}

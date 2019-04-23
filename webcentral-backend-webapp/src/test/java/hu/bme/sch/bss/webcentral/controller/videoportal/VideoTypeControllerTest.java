package hu.bme.sch.bss.webcentral.controller.videoportal;

import hu.bme.sch.bss.webcentral.videoportal.domain.VideoTypeRequest;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoTypeResponse;
import hu.bme.sch.bss.webcentral.videoportal.model.VideoType;
import hu.bme.sch.bss.webcentral.videoportal.service.VideoTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Péter Veress
 */

class VideoTypeControllerTest {
    private static final String DESCRIPTION = "description";
    private static final String LONG_NAME = "long name";
    private static final String CANONICAL_NAME = "canonical-name";
    private static final boolean NOT_ARCHIVED = true;
    private static final long ID = 16L;

    private VideoTypeController underTest;

    @Mock
    private VideoType mockType;
    @Mock
    private VideoTypeService mockService;
    @Mock
    private Logger mockLogger;

    @BeforeEach
    void init() {
        initMocks(this);
        underTest = new VideoTypeController(mockService, mockLogger);

        given(mockType.getDescription()).willReturn(DESCRIPTION);
        given(mockType.getLongName()).willReturn(LONG_NAME);
        given(mockType.getCanonicalName()).willReturn(CANONICAL_NAME);
        given(mockType.getArchived()).willReturn(NOT_ARCHIVED);
        given(mockType.getId()).willReturn(ID);
    }

    @Test
    void testCreateVideoType() {
        // GIVEN setup
        VideoTypeRequest mockRequest = mock(VideoTypeRequest.class);

        given(mockService.create(mockRequest)).willReturn(mockType);

        // WHEN
        VideoTypeResponse response = underTest.createVideoType(mockRequest);

        // THEN
        assertAll(
            () -> assertTrue(ID == response.getId()),
            () -> assertEquals(NOT_ARCHIVED, response.getArchived()),
            () -> assertEquals(LONG_NAME, response.getLongName()),
            () -> assertEquals(CANONICAL_NAME, response.getCanonicalName()),
            () -> assertEquals(DESCRIPTION, response.getDescription())
        );
    }

    @Test
    void testGetVideo() {
        // GIVEN
        given(mockService.findByCanonicalName(CANONICAL_NAME)).willReturn(mockType);

        // WHEN
        VideoTypeResponse response = underTest.getVideoType(CANONICAL_NAME);

        // THEN
        assertAll(
            () -> assertTrue(ID == response.getId()),
            () -> assertEquals(NOT_ARCHIVED, response.getArchived()),
            () -> assertEquals(LONG_NAME, response.getLongName()),
            () -> assertEquals(CANONICAL_NAME, response.getCanonicalName()),
            () -> assertEquals(DESCRIPTION, response.getDescription())
        );
    }

}

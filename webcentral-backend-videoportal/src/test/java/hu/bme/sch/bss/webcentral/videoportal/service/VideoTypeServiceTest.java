package hu.bme.sch.bss.webcentral.videoportal.service;

import hu.bme.sch.bss.webcentral.videoportal.dao.VideoTypeDao;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoTypeRequest;
import hu.bme.sch.bss.webcentral.videoportal.model.VideoType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Péter Veress
 */

class VideoTypeServiceTest {

    private static final String CANONICAL_NAME = "canonical-name";
    private static final String DESCRIPTION = "description";
    private static final String LONG_NAME = "long name";

    private VideoTypeService underTest;

    @Mock
    private Logger mockLogger;
    @Mock
    private VideoTypeDao mockDao;
    @Mock
    private VideoTypeRequest mockRequest;

    @BeforeEach
    private void init() {
        initMocks(this);
        underTest = new VideoTypeService(mockDao, mockLogger);

        given(mockRequest.getCanonicalName()).willReturn(CANONICAL_NAME);
        given(mockRequest.getDescription()).willReturn(DESCRIPTION);
        given(mockRequest.getLongName()).willReturn(LONG_NAME);
    }

    @Test
    void testCreate() {
        // GIVEN setup

        // WHEN
        VideoType result = underTest.create(mockRequest);

        // THEN
        then(mockDao).should().save(any(VideoType.class));
        assertAll(
            () -> assertEquals(CANONICAL_NAME, result.getCanonicalName()),
            () -> assertEquals(LONG_NAME, result.getLongName()),
            () -> assertEquals(DESCRIPTION, result.getDescription()),
            () -> assertFalse(result.getArchived())
        );
    }

    @Test
    void testFindTypeByCanonicalName(){
        // GIVEN
        VideoType mockType = mock(VideoType.class);
        given(mockDao.findByCanonicalName(anyString())).willReturn(Optional.of(mockType));

        // WHEN
        VideoType result = underTest.findByCanonicalName(CANONICAL_NAME);

        // THEN
        assertEquals(mockType, result);
    }

    @Test
    void testFindTypeByCanonicalNameShouldThrowExceptionWhenEntityNotFound() {
        // GIVEN
        given(mockDao.findByCanonicalName(anyString())).willReturn(Optional.empty());
        Exception exception = null;

        // WHEN
        try {
            underTest.findByCanonicalName(CANONICAL_NAME);
        } catch (Exception e){
            exception = e;
        }

        // THEN
        assertNotNull(exception);
        assertTrue(exception instanceof NoSuchElementException);

    }

}

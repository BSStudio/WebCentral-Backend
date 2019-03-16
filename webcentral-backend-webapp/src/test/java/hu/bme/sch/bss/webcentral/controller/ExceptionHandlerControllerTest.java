package hu.bme.sch.bss.webcentral.controller;

import hu.bme.sch.bss.webcentral.controller.domain.ErrorDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ExceptionHandlerControllerTest {
    private static final String TEST_MESSAGE = "test message";
    private static final String TEST_ADDRESS = "test.address";
    private static final String TEST_URI = "test/uri";

    private ExceptionHandlerController underTest;

    @BeforeEach
    void init() {
        underTest = new ExceptionHandlerController();
    }

    @Test
    void testNoSuchElementExceptionHandler() {
        // GIVEN
        Exception mockException = mock(NoSuchElementException.class);
        given(mockException.getMessage()).willReturn(TEST_MESSAGE);

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        given(mockRequest.getRemoteAddr()).willReturn(TEST_ADDRESS);
        given(mockRequest.getAttribute(any())).willReturn(TEST_URI);

        // WHEN
        ResponseEntity<ErrorDetails> responseEntity = underTest.noSuchElementExceptionHandler(mockException, mockRequest);

        // THEN
        assertAll(
            () -> assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode()),
            () -> assertEquals(HttpStatus.NOT_FOUND.value(), responseEntity.getBody().getStatus()),
            () -> assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getBody().getError()),
            () -> assertEquals(TEST_MESSAGE, responseEntity.getBody().getMessage()),
            () -> assertEquals(TEST_ADDRESS, responseEntity.getBody().getDetails()),
            () -> assertEquals(TEST_URI, responseEntity.getBody().getPath()),
        () -> assertNotNull(responseEntity.getBody().getTimestamp())
        );

    }

}

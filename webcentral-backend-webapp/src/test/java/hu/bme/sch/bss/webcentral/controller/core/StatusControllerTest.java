package hu.bme.sch.bss.webcentral.controller.core;

import hu.bme.sch.bss.webcentral.core.domain.StatusListResponse;
import hu.bme.sch.bss.webcentral.core.domain.StatusRequest;
import hu.bme.sch.bss.webcentral.core.domain.StatusResponse;
import hu.bme.sch.bss.webcentral.core.model.Status;
import hu.bme.sch.bss.webcentral.core.service.StatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

final class StatusControllerTest {

    private static final Long STATUS_ID = 8L;
    private static final String NAME = "name";
    private static final String OTHER_NAME = "other name";

    private StatusController underTest;
    private Status status;

    @Mock
    private StatusService mockStatusService;

    @Mock
    private Logger mockLogger;

    @BeforeEach
    void init() {
        initMocks(this);
        underTest = new StatusController(mockStatusService, mockLogger);
        status = Status.builder()
            .withName(NAME)
            .build();
    }

    @Test
    void testCreateUser() {
        // GIVEN
        StatusRequest request = StatusRequest.builder()
            .withName(NAME)
            .build();
        given(mockStatusService.create(request)).willReturn(status);

        // WHEN
        StatusResponse response = underTest.createStatus(request);

        // THEN
        assertEquals(request.getName(), response.getName());
    }

    @Test
    void testGetStatus() {
        // GIVEN
        Status actual = Status.builder()
            .withName(NAME)
            .build();

        given(mockStatusService.findById(STATUS_ID)).willReturn(actual);

        // WHEN
        StatusResponse response = underTest.getStatus(STATUS_ID);

        // THEN
        assertEquals(actual.getName(), response.getName());
    }

    @Test
    void testGetStatusShouldThrowNoSuchElementException() {
        // GIVEN
        given(mockStatusService.findById(STATUS_ID)).willThrow(new NoSuchElementException());

        // WHEN
        Exception exception = null;
        StatusResponse response = null;
        try {
            response = underTest.getStatus(STATUS_ID);
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertNotNull(exception);
        assertNull(response);
        verify(mockStatusService).findById(STATUS_ID);
    }

    @Test
    void testDeleteUser() {
        // GIVEN
        given(mockStatusService.findById(STATUS_ID)).willReturn(status);

        // WHEN
        underTest.deleteStatus(STATUS_ID);

        // THEN
        then(mockStatusService).should().findById(STATUS_ID);
        then(mockStatusService).should().delete(status);
    }

    @Test
    void testUpdateStatus() {
        // GIVEN
        final StatusRequest request = StatusRequest.builder()
            .withName(OTHER_NAME)
            .build();
        final Status serviceResult = Status.builder().withName(OTHER_NAME).build();
        given(mockStatusService.findById(STATUS_ID)).willReturn(status);
        given(mockStatusService.update(request, status)).willReturn(serviceResult);

        // WHEN
        final StatusResponse response = underTest.updateStatus(STATUS_ID, request);

        // THEN
        then(mockStatusService).should().findById(STATUS_ID);
        then(mockStatusService).should().update(request, status);

        assertEquals(request.getName(), response.getName());
    }

    @Test
    void testListAllStatuses() {
        // GIVEN
        final Status status2 = Status.builder()
                .build();
        final List<Status> statusList = List.of(status, status2);
        given(mockStatusService.findAll()).willReturn(statusList);

        // WHEN
        final StatusListResponse response = underTest.listAllStatuses();

        assertArrayEquals(statusList.toArray(), response.getStatuses());
    }
}

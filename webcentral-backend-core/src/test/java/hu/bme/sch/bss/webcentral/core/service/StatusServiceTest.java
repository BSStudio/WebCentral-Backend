package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.StatusDao;
import hu.bme.sch.bss.webcentral.core.domain.StatusRequest;
import hu.bme.sch.bss.webcentral.core.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.MockitoAnnotations.initMocks;

public class StatusServiceTest {
    private static final long STATUS_ID = 8L;
    private static final String NAME = "name";

    private static final String OTHER_NAME = "name";

    @Mock
    private StatusRequest mockStatusRequest;
    @Mock
    private Logger mockLogger;
    @Mock
    private StatusDao mockStatusDao;

    private Status status;
    private StatusService underTest;

    @BeforeEach
    void init() {
        initMocks(this);
        underTest = spy(new StatusService(mockStatusDao, mockLogger));

        given(mockStatusRequest.getName()).willReturn(OTHER_NAME);

        status = Status.builder()
            .withName(NAME)
            .build();
    }

    @Test
    void testCreateStatus() {
        // GIVEN
        doReturn(status).when(underTest).createStatusWithRequestData(mockStatusRequest);

        // WHEN
        Status result = underTest.create(mockStatusRequest);

        // THEN
        then(underTest).should().createStatusWithRequestData(mockStatusRequest);
        then(mockStatusDao).should().save(result);

        assertEquals(status, result);
    }

    @Test
    void testFindById() {
        // GIVEN
        Status status = Status.builder()
            .build();

        given(mockStatusDao.findById(STATUS_ID)).willReturn(Optional.of(status));

        // WHEN
        Status result = underTest.findById(STATUS_ID);

        // THEN
        assertEquals(status, result);
    }

    @Test
    void testFindByIdShouldThrowNoSuchElementException() {
        // GIVEN
        given(mockStatusDao.findById(any())).willReturn(Optional.empty());

        // WHEN
        NoSuchElementException exception = null;
        try {
            underTest.findById(STATUS_ID);
        } catch (NoSuchElementException e) {
            exception = e;
        }

        // THEN
        assertNotNull(exception);
    }

    @Test
    void testDelete() {
        // GIVEN setup

        // WHEN
        underTest.delete(status);

        // THEN
        then(mockStatusDao).should().delete(status);
    }

    @Test
    void testUpdate() {
        // GIVEN setup


        // WHEN
        underTest.update(mockStatusRequest, status);

        // THEN
        then(mockStatusRequest).should().getName();
        assertEquals(OTHER_NAME, status.getName());
    }

    @Test
    void testCreateStatusWithRequestData() {
        // GIVEN setup

        // WHEN
        Status result = underTest.createStatusWithRequestData(mockStatusRequest);

        // THEN
        then(mockStatusRequest).should().getName();

        assertEquals(OTHER_NAME, result.getName());
    }
    @Test
    void testFindAll() {
        // GIVEN setup
        List<Status> statusList = new ArrayList<>();

        Status status2 = Status.builder()
            .build();

        statusList.add(status);
        statusList.add(status2);

        given(mockStatusDao.findAll()).willReturn(statusList);

        // WHEN
        List<Status> result = underTest.findAll();

        // THEN
        assertEquals(statusList, result);
    }
}

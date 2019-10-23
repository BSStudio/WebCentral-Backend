package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.StatusDao;
import hu.bme.sch.bss.webcentral.core.domain.StatusRequest;
import hu.bme.sch.bss.webcentral.core.model.Status;
import hu.bme.sch.bss.webcentral.core.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.slf4j.Logger;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

final class StatusServiceTest {

    private static final long STATUS_ID = 8L;
    private static final String NAME = "name";

    private static final String UPDATED_NAME = "other name";

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

        given(mockStatusRequest.getName()).willReturn(NAME);

        status = spy(Status.builder()
                .withName(NAME)
                .build());
    }

    @Test
    void testCreateStatus() {
        // GIVEN
        doReturn(status).when(mockStatusDao).save(any(Status.class));

        // WHEN
        final Status result = underTest.create(mockStatusRequest);

        // THEN
        verify(mockStatusRequest).getName();
        assertEquals(result, status);
    }

    @Test
    void testFindById() {
        // GIVEN
        given(mockStatusDao.findById(STATUS_ID)).willReturn(Optional.of(status));

        // WHEN
        final Status result = underTest.findById(STATUS_ID);

        // THEN
        assertEquals(result, status);
    }

    @Test
    void testFindByIdShouldThrowNoSuchElementException() {
        // GIVEN
        given(mockStatusDao.findById(any())).willReturn(Optional.empty());

        // WHEN
        final Executable testSubject = () -> underTest.findById(STATUS_ID);

        // THEN
        assertThrows(NoSuchElementException.class, testSubject);
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
        final Status updated = Status.builder()
                .withName(UPDATED_NAME).build();
        given(mockStatusRequest.getName()).willReturn(UPDATED_NAME);
        doReturn(updated).when(mockStatusDao).save(status);

        // WHEN
        final Status result = underTest.update(mockStatusRequest, status);

        // THEN
        verify(mockStatusDao).save(status);
        verify(mockStatusRequest).getName();
        verify(status).setName(mockStatusRequest.getName());
        assertEquals(result.getName(), mockStatusRequest.getName());
    }

    @Test
    void testFindAll() {
        // GIVEN setup
        final Status status2 = Status.builder()
                .build();
        final List<Status> statusList = List.of(status, status2);
        given(mockStatusDao.findAll()).willReturn(statusList);

        // WHEN
        final List<Status> result = underTest.findAll();

        // THEN
        assertEquals(result, statusList);
    }

    @Test
    void testFindByName() {
        // GIVEN
        final Status status = Status.builder()
                .withName(NAME)
                .build();
        given(mockStatusDao.findByName(NAME)).willReturn(Optional.of(status));

        // WHEN
        final Status result = underTest.findByName(NAME);

        // THEN
        assertEquals(result, status);
    }

    @Test
    void testFindByNameShouldThrowNoSuchElementException() {
        // GIVEN
        given(mockStatusDao.findByName(any())).willReturn(Optional.empty());

        // WHEN
        final Executable testSubject = () -> underTest.findByName(NAME);

        // THEN
        assertThrows(NoSuchElementException.class, testSubject);
    }

    @Test
    void testFindAllUserById() {
        // GIVEN
        final Set<User> userSet = Set.of(User.builder().build());
        doReturn(status).when(underTest).findById(STATUS_ID);
        doReturn(userSet).when(status).getUsers();

        // WHEN
        final Set<User> result = underTest.findAllUserByStatusId(STATUS_ID);

        // THEN
        assertEquals(result, userSet);
    }

}

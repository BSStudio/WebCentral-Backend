package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.PositionDao;
import hu.bme.sch.bss.webcentral.core.domain.PositionRequest;
import hu.bme.sch.bss.webcentral.core.model.Position;
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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

final class PositionServiceTest {

    private static final long POSITION_ID = 8L;
    private static final String NAME = "name";

    private static final String UPDATED_NAME = "other name";

    @Mock
    private PositionRequest mockPositionRequest;
    @Mock
    private Logger mockLogger;
    @Mock
    private PositionDao mockPositionDao;

    private Position position;
    private PositionService underTest;

    @BeforeEach
    void init() {
        initMocks(this);
        underTest = spy(new PositionService(mockPositionDao, mockLogger));

        given(mockPositionRequest.getName()).willReturn(NAME);

        position = spy(Position.builder()
            .withName(NAME)
            .build());
    }

    @Test
    void testCreatePosition() {
        // GIVEN
        doReturn(position).when(mockPositionDao).save(any(Position.class));

        // WHEN
        final Position result = underTest.create(mockPositionRequest);

        // THEN
        verify(mockPositionRequest).getName();
        assertEquals(result.getName(), mockPositionRequest.getName());
    }

    @Test
    void testFindById() {
        // GIVEN
        given(mockPositionDao.findById(POSITION_ID)).willReturn(Optional.of(position));

        // WHEN
        final Position result = underTest.findById(POSITION_ID);

        // THEN
        assertEquals(result, position);
    }

    @Test
    void testFindByIdShouldThrowNoSuchElementException() {
        // GIVEN
        given(mockPositionDao.findById(any())).willReturn(Optional.empty());

        // WHEN
        final Executable testSubject = () -> underTest.findById(POSITION_ID);

        // THEN
        assertThrows(NoSuchElementException.class, testSubject);
    }

    @Test
    void testDelete() {
        // GIVEN setup

        // WHEN
        underTest.delete(position);

        // THEN
        verify(mockPositionDao).delete(position);
    }

    @Test
    void testUpdate() {
        // GIVEN setup
        final Position updated = Position.builder()
                .withName(UPDATED_NAME).build();
        given(mockPositionRequest.getName()).willReturn(UPDATED_NAME);
        doReturn(updated).when(mockPositionDao).save(position);

        // WHEN
        final Position result = underTest.update(mockPositionRequest, position);

        // THEN
        verify(mockPositionDao).save(position);
        verify(mockPositionRequest).getName();
        verify(position).setName(mockPositionRequest.getName());
        assertEquals(result.getName(), mockPositionRequest.getName());
    }

    @Test
    void testFindAll() {
        // GIVEN setup
        final Position position2 = Position.builder()
                .build();
        final List<Position> positionList = List.of(position, position2);
        given(mockPositionDao.findAll()).willReturn(positionList);

        // WHEN
        final List<Position> result = underTest.findAll();

        // THEN
        assertEquals(result, positionList);
    }

    @Test
    void testFindByName() {
        // GIVEN
        final Position position = Position.builder()
                .withName(NAME)
                .build();
        given(mockPositionDao.findByName(NAME)).willReturn(Optional.of(position));

        // WHEN
        final Position result = underTest.findByName(NAME);

        // THEN
        assertEquals(result, position);
    }

    @Test
    void testFindByNameShouldThrowNoSuchElementException() {
        // GIVEN
        given(mockPositionDao.findByName(any())).willReturn(Optional.empty());

        // WHEN
        final Executable testSubject = () -> underTest.findByName(NAME);

        // THEN
        assertThrows(NoSuchElementException.class, testSubject);
    }

    @Test
    void testFindAllUserById() {
        // GIVEN
        final Set<User> userSet = Set.of(User.builder().build());
        doReturn(position).when(underTest).findById(POSITION_ID);
        doReturn(userSet).when(position).getUsers();

        // WHEN
        final Set<User> result = underTest.findAllUserById(POSITION_ID);

        // THEN
        assertEquals(result, userSet);
    }

}

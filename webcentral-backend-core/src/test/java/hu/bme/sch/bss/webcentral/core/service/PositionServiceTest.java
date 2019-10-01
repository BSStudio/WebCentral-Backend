package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.PositionDao;
import hu.bme.sch.bss.webcentral.core.domain.PositionRequest;
import hu.bme.sch.bss.webcentral.core.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.MockitoAnnotations.initMocks;

final class PositionServiceTest {

    private static final long POSITION_ID = 8L;
    private static final String NAME = "name";

    private static final String OTHER_NAME = "other name";

    private PositionRequest positionRequest;
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

        positionRequest = PositionRequest.builder()
                .withName(OTHER_NAME)
                .build();

        position = Position.builder()
            .withName(NAME)
            .build();
    }

    @Test
    void testCreatePosition() {
        // GIVEN
        doReturn(position).when(underTest).create(positionRequest);

        // WHEN
        final Position result = underTest.create(positionRequest);

        // THEN
        assertEquals(position, result);
    }

    @Test
    void testFindById() {
        // GIVEN
        Position position = Position.builder()
            .build();
        given(mockPositionDao.findById(POSITION_ID)).willReturn(Optional.of(position));

        // WHEN
        final Position result = underTest.findById(POSITION_ID);

        // THEN
        assertEquals(position, result);
    }

    @Test
    void testFindByIdShouldThrowNoSuchElementException() {
        // GIVEN
        given(mockPositionDao.findById(any())).willReturn(Optional.empty());

        // WHEN
         final Executable testSubject = () -> {
             underTest.findById(POSITION_ID);
         };

        // THEN
        assertThrows(NoSuchElementException.class, testSubject);
    }

    @Test
    void testDelete() {
        // GIVEN setup

        // WHEN
        underTest.delete(position);

        // THEN
        then(mockPositionDao).should().delete(position);
    }

    @Test
    void testUpdate() {
        // GIVEN setup


        // WHEN
        underTest.update(positionRequest, position);

        // THEN
        then(positionRequest).should().getName();
        assertEquals(OTHER_NAME, position.getName());
    }

    @Test
    void testCreatePositionWithRequestData() {
        // GIVEN setup
        Position saved = Position.builder()
                .withName(OTHER_NAME)
                .build();
        given(mockPositionDao.save(position)).willReturn(saved);

        // WHEN
        final Position result =  underTest.create(positionRequest);

        // THEN
        then(positionRequest).should().getName();

        assertEquals(OTHER_NAME, result.getName());
    }

    @Test
    void testFindAll() {
        // GIVEN setup
        List<Position> positionList = new ArrayList<>();

        Position position2 = Position.builder()
            .build();

        positionList.add(position);
        positionList.add(position2);

        given(mockPositionDao.findAll()).willReturn(positionList);

        // WHEN
        List<Position> result = underTest.findAll();

        // THEN
        assertEquals(positionList, result);
    }
    
    @Test
    void testFindByName() {
        // GIVEN
        Position position = Position.builder()
            .withName(NAME)
            .build();
        given(mockPositionDao.findByName(NAME)).willReturn(Optional.of(position));
        
        // WHEN
        Position result = underTest.findByName(NAME);
    
        // THEN
        assertEquals(position, result);
    }

    @Test
    void testFindByNameShouldThrowNoSuchElementException() {
        // GIVEN
        given(mockPositionDao.findByName(any())).willReturn(Optional.empty());

        // WHEN
        NoSuchElementException exception = null;
        try {
            underTest.findByName(NAME);
        } catch (NoSuchElementException e) {
            exception = e;
        }

        // THEN
        assertNotNull(exception);
    }
}

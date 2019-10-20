package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.PositionDao;
import hu.bme.sch.bss.webcentral.core.domain.PositionRequest;
import hu.bme.sch.bss.webcentral.core.model.Position;
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

final class PositionServiceTest {

    private static final long POSITION_ID = 8L;
    private static final String NAME = "name";

    private static final String OTHER_NAME = "other name";

    @Mock
    private Logger mockLogger;
    @Mock
    private PositionDao mockPositionDao;

    private Position position;
    private PositionRequest positionRequest;
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
        doReturn(position).when(underTest).createPositionWithRequestData(positionRequest);

        // WHEN
        Position result = underTest.create(positionRequest);

        // THEN
        then(underTest).should().createPositionWithRequestData(positionRequest);
        then(mockPositionDao).should().save(result);

        assertEquals(position, result);
    }

    @Test
    void testFindById() {
        // GIVEN
        Position position = Position.builder()
            .build();

        given(mockPositionDao.findById(POSITION_ID)).willReturn(Optional.of(position));

        // WHEN
        Position result = underTest.findById(POSITION_ID);

        // THEN
        assertEquals(position, result);
    }

    @Test
    void testFindByIdShouldThrowNoSuchElementException() {
        // GIVEN
        given(mockPositionDao.findById(any())).willReturn(Optional.empty());

        // WHEN
        NoSuchElementException exception = null;
        try {
            underTest.findById(POSITION_ID);
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
        assertEquals(OTHER_NAME, position.getName());
    }

    @Test
    void testCreatePositionWithRequestData() {
        // GIVEN setup

        // WHEN
        Position result = underTest.createPositionWithRequestData(positionRequest);

        // THEN
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
}

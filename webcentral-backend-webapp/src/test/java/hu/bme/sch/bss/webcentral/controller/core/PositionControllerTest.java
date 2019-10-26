package hu.bme.sch.bss.webcentral.controller.core;

import hu.bme.sch.bss.webcentral.core.domain.PositionListResponse;
import hu.bme.sch.bss.webcentral.core.domain.PositionRequest;
import hu.bme.sch.bss.webcentral.core.domain.PositionResponse;
import hu.bme.sch.bss.webcentral.core.model.Position;
import hu.bme.sch.bss.webcentral.core.service.PositionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import org.mockito.Mock;

import org.slf4j.Logger;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

final class PositionControllerTest {

    private static final Long POSITION_ID = 8L;
    private static final String NAME = "name";
    private static final String OTHER_NAME = "other name";

    private PositionController underTest;
    private Position position;

    @Mock
    private PositionService mockPositionService;

    @Mock
    private Logger mockLogger;

    @BeforeEach
    void init() {
        initMocks(this);
        underTest = new PositionController(mockPositionService, mockLogger);
        position = Position.builder()
            .withName(NAME)
            .build();
    }

    @Test
    void testCreateUser() {
        // GIVEN
        final PositionRequest request = PositionRequest.builder()
            .withName(NAME)
            .build();
        given(mockPositionService.create(request)).willReturn(position);

        // WHEN
        final PositionResponse response = underTest.createUser(request);

        // THEN
        assertEquals(request.getName(), response.getName());
    }

    @Test
    void testGetPosition() {
        // GIVEN
        final Position actual = Position.builder()
            .withName(NAME)
            .build();

        given(mockPositionService.findById(POSITION_ID)).willReturn(actual);

        // WHEN
        final PositionResponse response = underTest.getPosition(POSITION_ID);

        // THEN
        assertEquals(actual.getName(), response.getName());
    }

    @Test
    void testGetPositionShouldThrowNoSuchElementException() {
        // GIVEN
        given(mockPositionService.findById(POSITION_ID)).willThrow(new NoSuchElementException());

        // WHEN
        final Executable testSubject = () -> underTest.getPosition(POSITION_ID);

        // THEN
        assertThrows(NoSuchElementException.class, testSubject);
        verify(mockPositionService).findById(POSITION_ID);
    }

    @Test
    void testDeleteUser() {
        // GIVEN
        given(mockPositionService.findById(POSITION_ID)).willReturn(position);

        // WHEN
        underTest.deletePosition(POSITION_ID);

        // THEN
        then(mockPositionService).should().findById(POSITION_ID);
        then(mockPositionService).should().delete(position);
    }

    @Test
    void testUpdatePosition() {
        // GIVEN
        final PositionRequest request = PositionRequest.builder()
            .withName(OTHER_NAME)
            .build();
        final Position result = Position.builder().withName(OTHER_NAME).build();
        given(mockPositionService.findById(POSITION_ID)).willReturn(position);
        given(mockPositionService.update(request, position)).willReturn(result);

        // WHEN
        final PositionResponse response = underTest.updatePosition(POSITION_ID, request);

        // THEN
        then(mockPositionService).should().findById(POSITION_ID);
        then(mockPositionService).should().update(request, position);

        assertEquals(request.getName(), response.getName());
    }

    @Test
    void testListAllPosition() {
        // GIVEN
        final Position position2 = Position.builder()
                .build();
        final List<Position> positionList = List.of(position, position2);

        given(mockPositionService.findAll()).willReturn(positionList);

        // WHEN
        final PositionListResponse response = underTest.listAllPositions();

        assertArrayEquals(positionList.toArray(), response.getPositions());
    }
}

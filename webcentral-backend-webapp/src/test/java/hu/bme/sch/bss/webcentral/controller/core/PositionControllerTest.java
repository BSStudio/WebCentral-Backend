package hu.bme.sch.bss.webcentral.controller.core;

import hu.bme.sch.bss.webcentral.core.domain.PositionRequest;
import hu.bme.sch.bss.webcentral.core.domain.PositionResponse;
import hu.bme.sch.bss.webcentral.core.model.Position;
import hu.bme.sch.bss.webcentral.core.service.PositionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class PositionControllerTest {

    private static final Long POSITION_ID = 8L;
    private static final String NAME = "name";

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
        PositionRequest request = PositionRequest.builder()
            .withName(NAME)
            .build();
        given(mockPositionService.create(request)).willReturn(position);

        // WHEN
        PositionResponse response = underTest.createUser(request);

        // THEN
        assertEquals(request.getName(), response.getName());
    }
}

package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.PositionDao;
import hu.bme.sch.bss.webcentral.core.domain.PositionRequest;
import hu.bme.sch.bss.webcentral.core.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.MockitoAnnotations.initMocks;

public class PositionServiceTest {

    private static final long POSITION_ID = 8L;
    private static final String NAME = "name";

    private static final String OTHER_NAME = "name";

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

        given(mockPositionRequest.getName()).willReturn(OTHER_NAME);

        position = Position.builder()
            .withName(NAME)
            .build();
    }

    @Test
    void testCreatePosition() {
        // GIVEN
        doReturn(position).when(underTest).createPositionWithRequestData(mockPositionRequest);

        // WHEN
        Position result = underTest.create(mockPositionRequest);

        // THEN
        then(underTest).should().createPositionWithRequestData(mockPositionRequest);
        then(mockPositionDao).should().save(result);

        assertEquals(position, result);
    }
    
    @Test
    void testCreatePositionWithRequestData() {
        // GIVEN setup
        
        // WHEN
        Position result = underTest.createPositionWithRequestData(mockPositionRequest);
    
        // THEN
        then(mockPositionRequest).should().getName();

        assertEquals(OTHER_NAME, result.getName());
    }
}

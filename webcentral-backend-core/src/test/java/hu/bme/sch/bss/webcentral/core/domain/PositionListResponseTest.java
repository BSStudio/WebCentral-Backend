package hu.bme.sch.bss.webcentral.core.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hu.bme.sch.bss.webcentral.core.model.Position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

final class PositionListResponseTest {

    private Position position1;
    private Position position2;

    private PositionListResponse underTest;

    @BeforeEach
    void initTests() {
        position1 = Position.builder().build();
        position2 = Position.builder().build();
    }

    @Test
    void testConstructorWithList() {
        //GIVEN
        final List<Position> positionList = List.of(position1, position2);

        //WHEN
        underTest = new PositionListResponse(positionList);

        //THEN
        assertEquals(positionList, Arrays.asList(underTest.getPositions()));
    }

    @Test
    void testConstructorWithArray() {
        //GIVEN
        final Position[] positionList = new Position[2];
        positionList[0] = position1;
        positionList[1] = position2;

        //WHEN
        underTest = new PositionListResponse(positionList);

        //THEN
        assertEquals(positionList, underTest.getPositions());
    }

}

package hu.bme.sch.bss.webcentral.core.domain;

import hu.bme.sch.bss.webcentral.core.model.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionListResponseTest {
    @Test
    void testBuilderWithList() {
        //GIVEN
        List<Position> PositionList = new ArrayList<>();

        Position Position1 = Position.builder().build();
        Position Position2 = Position.builder().build();

        PositionList.add(Position1);
        PositionList.add(Position2);

        //WHEN
        PositionListResponse response = PositionListResponse.builder()
            .withPositions(PositionList)
            .build();

        //THEN
        assertEquals(PositionList, Arrays.asList(response.getPositions()));
    }

    @Test
    void testBuilderWithArray() {
        //GIVEN
        Position[] PositionList = new Position[2];

        Position Position1 = Position.builder().build();
        Position Position2 = Position.builder().build();

        PositionList[0] = Position1;
        PositionList[1] = Position2;

        //WHEN
        PositionListResponse response = PositionListResponse.builder()
            .withPositions(PositionList)
            .build();

        //THEN
        assertEquals(PositionList, response.getPositions());
    }
}

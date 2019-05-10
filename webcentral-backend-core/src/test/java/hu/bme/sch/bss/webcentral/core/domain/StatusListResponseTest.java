package hu.bme.sch.bss.webcentral.core.domain;

import hu.bme.sch.bss.webcentral.core.model.Status;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StatusListResponseTest {
    @Test
    void testBuilderWithList() {
        //GIVEN
        List<Status> statusList = new ArrayList<>();

        Status status1 = Status.builder().build();
        Status status2 = Status.builder().build();

        statusList.add(status1);
        statusList.add(status2);

        //WHEN
        StatusListResponse response = StatusListResponse.builder()
            .withStatuses(statusList)
            .build();

        //THEN
        assertEquals(statusList, Arrays.asList(response.getStatuses()));
    }

    @Test
    void testBuilderWithArray() {
        //GIVEN
        Status[] statusList = new Status[2];

        Status status1 = Status.builder().build();
        Status status2 = Status.builder().build();

        statusList[0] = status1;
        statusList[1] = status2;

        //WHEN
        StatusListResponse response = StatusListResponse.builder()
            .withStatuses(statusList)
            .build();

        //THEN
        assertEquals(statusList, response.getStatuses());
    }
}

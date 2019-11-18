package hu.bme.sch.bss.webcentral.core.domain;

import hu.bme.sch.bss.webcentral.core.model.Status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

final class StatusListResponseTest {

    private Status status1;
    private Status status2;

    private StatusListResponse underTest;

    @BeforeEach
    void init() {
        status1 = Status.builder().build();
        status2 = Status.builder().build();
    }

    @Test
    void testBuilderWithList() {
        //GIVEN
        final List<Status> statusList = List.of(status1, status2);

        //WHEN
        underTest = StatusListResponse.builder()
            .withStatuses(statusList)
            .build();

        //THEN
        assertArrayEquals(statusList.toArray(), underTest.getStatuses());
    }

    @Test
    void testBuilderWithArray() {
        //GIVEN
        final Status[] statusList = new Status[2];
        statusList[0] = status1;
        statusList[1] = status2;

        //WHEN
        underTest = StatusListResponse.builder()
            .withStatuses(statusList)
            .build();

        //THEN
        assertArrayEquals(statusList, underTest.getStatuses());
    }

}

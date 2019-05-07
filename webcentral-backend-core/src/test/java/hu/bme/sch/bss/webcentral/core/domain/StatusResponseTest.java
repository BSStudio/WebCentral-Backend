package hu.bme.sch.bss.webcentral.core.domain;

import hu.bme.sch.bss.webcentral.core.model.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusResponseTest {

    private static final String NAME = "name";

    private StatusResponse underTest;

    @Test
    void testConstructorAndGetters() {
        // GIVEN
        Status position = Status.builder()
            .withName(NAME)
            .build();

        // WHEN
        underTest = new StatusResponse(position);

        // THEN
        assertEquals(position.getId(), underTest.getId());
        assertEquals(position.getName(), underTest.getName());
    }
}

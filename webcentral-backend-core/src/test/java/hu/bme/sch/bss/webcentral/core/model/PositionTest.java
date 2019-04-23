package hu.bme.sch.bss.webcentral.core.model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PositionTest {

    private static final String NAME = "name";

    private Position underTest;

    @Test
    void testConstructorAndGetters() {
        // GIVEN

        // WHEN
        underTest = getDefaultValuesBuilder()
            .build();

        // THEN
        assertEquals(NAME, underTest.getName());
    }

    @Test
    void testNoArgConstructor() {
        // GIVEN

        // WHEN
        underTest = new Position();

        // THEN
        assertNull(underTest.getName());
    }

    private Position.Builder getDefaultValuesBuilder() {
        return Position.builder()
            .withName(NAME);
    }
}

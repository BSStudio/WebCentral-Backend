package hu.bme.sch.bss.webcentral.core.model;


import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static hu.bme.sch.bss.webcentral.WcTestUtil.thenValidationFails;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

final class PositionTest {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
            .getValidator();

    private static final String NAME = "name";

    private static final String OTHER_NAME = "other name";

    private Position underTest;

    @Test
    void testConstructorAndGetters() {
        // GIVEN

        // WHEN
        underTest = Position.builder()
                .withName(NAME)
                .build();

        // THEN
        assertAll(
                () -> assertEquals(NAME, underTest.getName()),
                () -> assertNull(underTest.getUsers())
        );
    }

    @Test
    void testNoArgConstructor() {
        // GIVEN

        // WHEN
        underTest = new Position();

        // THEN
        assertNull(underTest.getId());
        assertNull(underTest.getName());
    }

    @Test
    void testValidationShouldFailForMissingName() {
        // GIVEN
        underTest = Position.builder()
                .build();

        // WHEN
        final Set<ConstraintViolation<Position>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "name");
    }

    @Test
    void testSetName() {
        // GIVEN
        underTest = Position.builder()
                .withName(NAME)
                .build();

        // WHEN
        underTest.setName(OTHER_NAME);

        // THEN
        assertEquals(OTHER_NAME, underTest.getName());
    }
}

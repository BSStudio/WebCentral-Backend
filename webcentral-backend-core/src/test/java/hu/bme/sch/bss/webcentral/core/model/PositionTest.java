package hu.bme.sch.bss.webcentral.core.model;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static hu.bme.sch.bss.webcentral.WcTestUtil.thenValidationFails;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

final class PositionTest {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
            .getValidator();

    private static final String NAME = "name";

    private Position underTest;

    @Test
    public void testConstructor() {
        // GIVEN
        final Position.Builder builder = Position.builder()
                .withName(NAME);

        // WHEN
        underTest = builder
                .build();

        // THEN
        assertEquals(NAME, underTest.getName());
    }

    @Test
    public void testNoArgConstructor() {
        // GIVEN

        // WHEN
        underTest = new Position();

        // THEN
        assertNull(underTest.getId());
        assertNull(underTest.getName());
    }


    @Test
    public void testValidationShouldFailForMissingName() {
        // GIVEN
        underTest = Position.builder()
                .build();

        // WHEN
        Set<ConstraintViolation<Position>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "name");
    }

}

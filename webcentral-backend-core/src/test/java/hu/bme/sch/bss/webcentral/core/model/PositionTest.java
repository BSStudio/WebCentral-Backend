package hu.bme.sch.bss.webcentral.core.model;


import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

final class PositionTest {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
            .getValidator();

    private static final String NAME = "name";

    private static final String OTHER_NAME = "other name";

    private static final String TO_STRING_RESULT = "Position{id=null, name='name'}";

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
    void testEqualsAndHash() {
        // GIVEN
        final Position.Builder builder = Position.builder()
                .withName(NAME);

        // WHEN
        final Position validPosition1 = Position.builder()
                .withName(NAME).build();
        final Position validPosition2 = Position.builder()
                .withName(NAME).build();
        final Position invalidPosition = builder
                .withName("something else")
                .build();

        // THEN
        assertEquals(validPosition1, validPosition2);
        assertEquals(validPosition1, validPosition1);
        assertNotEquals(validPosition1, invalidPosition);
        assertNotEquals(validPosition2, invalidPosition);
        assertNotEquals(validPosition2, null);
        assertNotEquals(validPosition2, new Object());
        assertEquals(validPosition1.hashCode(), validPosition2.hashCode());
    }

    @Test
    void testToString() {
        // GIVEN
        underTest = Position.builder()
                .withName(NAME)
                .build();

        // WHEN

        // THEN
        assertEquals(TO_STRING_RESULT, underTest.toString());
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

    private void thenValidationFails(Set<ConstraintViolation<Position>> violations,
                                     String expectedMessage, String expectedProperty) {
        assertThat(violations.size(), is(1));
        ConstraintViolation<Position> violation = violations.stream().findFirst().get();
        assertThat(violation.getMessage(), is(expectedMessage));
        assertThat(violation.getPropertyPath().toString(), is(expectedProperty));
    }

}

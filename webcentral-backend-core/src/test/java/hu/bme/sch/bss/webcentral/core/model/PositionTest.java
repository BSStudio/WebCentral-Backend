package hu.bme.sch.bss.webcentral.core.model;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PositionTest {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
        .getValidator();

    private static final String NAME = "name";

    private static final String OTHER_NAME = "other name";

    private static final String TO_STRING_RESULT = "Position{id=null, name='name'}";

    private Position underTest;

    @Test
    public void testConstructorAndGetters() {
        // GIVEN

        // WHEN
        underTest = getDefaultValuesBuilder()
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
    public void testEqualsAndHash() {
        // GIVEN
        Position.Builder builder = getDefaultValuesBuilder();

        // WHEN
        Position validPosition1 = getDefaultValuesBuilder().build();
        Position validPosition2 = getDefaultValuesBuilder().build();
        Position invalidPosition = builder
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
    public void testToString() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .build();

        // WHEN

        // THEN
        assertEquals(TO_STRING_RESULT, underTest.toString());
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

    @Test
    public void testSetName() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .build();

        // WHEN
        underTest.setName(OTHER_NAME);

        // THEN
        assertEquals(OTHER_NAME, underTest.getName());
    }

    private Position.Builder getDefaultValuesBuilder() {
        return Position.builder()
            .withName(NAME);
    }

    private void thenValidationFails(Set<ConstraintViolation<Position>> violations,
                                     String expectedMessage, String expectedProperty) {
        assertThat(violations.size(), is(1));
        ConstraintViolation<Position> violation = violations.stream().findFirst().get();
        assertThat(violation.getMessage(), is(expectedMessage));
        assertThat(violation.getPropertyPath().toString(), is(expectedProperty));
    }
}

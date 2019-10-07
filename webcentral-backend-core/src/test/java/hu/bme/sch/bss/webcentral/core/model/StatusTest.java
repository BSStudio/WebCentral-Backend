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

final class StatusTest {
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
        .getValidator();

    private static final String NAME = "name";

    private static final String OTHER_NAME = "other name";

    private static final String TO_STRING_RESULT = "Status{id=null, name='name'}";

    private Status underTest;

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
        underTest = new Status();

        // THEN
        assertNull(underTest.getId());
        assertNull(underTest.getName());
    }

    @Test
    public void testEqualsAndHash() {
        // GIVEN
        Status.Builder builder = getDefaultValuesBuilder();

        // WHEN
        Status validStatus1 = getDefaultValuesBuilder().build();
        Status validStatus2 = getDefaultValuesBuilder().build();
        Status invalidStatus = builder
            .withName("something else")
            .build();

        // THEN
        assertEquals(validStatus1, validStatus2);
        assertEquals(validStatus1, validStatus1);
        assertNotEquals(validStatus1, invalidStatus);
        assertNotEquals(validStatus2, invalidStatus);
        assertNotEquals(validStatus2, null);
        assertNotEquals(validStatus2, new Object());
        assertEquals(validStatus1.hashCode(), validStatus2.hashCode());
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
        underTest = Status.builder()
            .build();

        // WHEN
        Set<ConstraintViolation<Status>> violations = VALIDATOR.validate(underTest);

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

    private Status.Builder getDefaultValuesBuilder() {
        return Status.builder()
            .withName(NAME);
    }

    private void thenValidationFails(Set<ConstraintViolation<Status>> violations,
                                     String expectedMessage, String expectedProperty) {
        assertThat(violations.size(), is(1));
        ConstraintViolation<Status> violation = violations.stream().findFirst().get();
        assertThat(violation.getMessage(), is(expectedMessage));
        assertThat(violation.getPropertyPath().toString(), is(expectedProperty));
    }
}

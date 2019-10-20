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

final class StatusTest {
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
        .getValidator();

    private static final String NAME = "name";

    private static final String OTHER_NAME = "other name";

    private static final String TO_STRING_RESULT = "Status{id=null, name='name'}";

    private Status underTest;

    @Test
    void testConstructorAndGetters() {
        // GIVEN

        // WHEN
        underTest = Status.builder()
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
        underTest = new Status();

        // THEN
        assertNull(underTest.getId());
        assertNull(underTest.getName());
    }

    @Test
    void testEqualsAndHash() {
        // GIVEN
        final Status.Builder builder = Status.builder()
                .withName(NAME);

        // WHEN
        final Status validStatus1 = Status.builder()
                .withName(NAME).build();
        final Status validStatus2 = Status.builder()
                .withName(NAME).build();
        final Status invalidStatus = builder
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
    void testToString() {
        // GIVEN
        underTest = Status.builder()
                .withName(NAME)
            .build();

        // WHEN

        // THEN
        assertEquals(TO_STRING_RESULT, underTest.toString());
    }

    @Test
    void testValidationShouldFailForMissingName() {
        // GIVEN
        underTest = Status.builder()
            .build();

        // WHEN
        Set<ConstraintViolation<Status>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "name");
    }

    @Test
    void testSetName() {
        // GIVEN
        underTest = Status.builder()
                .withName(NAME)
            .build();

        // WHEN
        underTest.setName(OTHER_NAME);

        // THEN
        assertEquals(OTHER_NAME, underTest.getName());
    }

    private void thenValidationFails(Set<ConstraintViolation<Status>> violations,
                                     String expectedMessage, String expectedProperty) {
        assertThat(violations.size(), is(1));
        ConstraintViolation<Status> violation = violations.stream().findFirst().get();
        assertThat(violation.getMessage(), is(expectedMessage));
        assertThat(violation.getPropertyPath().toString(), is(expectedProperty));
    }
}

package hu.bme.sch.bss.webcentral.core.domain;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusRequestTest {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
        .getValidator();
    private static final String NAME = "name";

    private StatusRequest underTest;

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
    void testValidationShouldFailForBlankName() {
        // GIVEN
        underTest = StatusRequest.builder()
            .build();

        // WHEN
        Set<ConstraintViolation<StatusRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "name");
    }

    private StatusRequest.Builder getDefaultValuesBuilder() {
        return StatusRequest.builder()
            .withName(NAME);
    }

    private void thenValidationFails(Set<ConstraintViolation<StatusRequest>> violations,
                                     String expectedMessage, String expectedProperty) {
        assertThat(violations.size(), is(1));
        ConstraintViolation<StatusRequest> violation = violations.stream().findFirst().get();
        assertThat(violation.getMessage(), is(expectedMessage));
        assertThat(violation.getPropertyPath().toString(), is(expectedProperty));
    }
}


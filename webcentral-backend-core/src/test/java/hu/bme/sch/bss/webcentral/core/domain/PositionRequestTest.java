package hu.bme.sch.bss.webcentral.core.domain;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class PositionRequestTest {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
    private static final String NAME = "name";

    private PositionRequest underTest;

    @Test
    void testConstructorAndGetters() {
        // GIVEN

        // WHEN
        underTest = PositionRequest.builder()
                .withName(NAME)
                .build();

        // THEN
        assertEquals(NAME, underTest.getName());
    }

    @Test
    void testValidationShouldFailForBlankName() {
        // GIVEN
        underTest = PositionRequest.builder()
                .withName("")
                .build();

        // WHEN
        final Set<ConstraintViolation<PositionRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "name");
    }

    private void thenValidationFails(Set<ConstraintViolation<PositionRequest>> violations,
                                     String expectedMessage, String expectedProperty) {
        assertThat(violations.size(), is(1));
        ConstraintViolation<PositionRequest> violation = violations.stream().findFirst().get();
        assertThat(violation.getMessage(), is(expectedMessage));
        assertThat(violation.getPropertyPath().toString(), is(expectedProperty));
    }

}

package hu.bme.sch.bss.webcentral.core.domain;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static hu.bme.sch.bss.webcentral.WcTestUtil.thenValidationFails;
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
}

package hu.bme.sch.bss.webcentral.core.model;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static hu.bme.sch.bss.webcentral.WcTestUtil.thenValidationFails;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

final class StatusTest {
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
        .getValidator();

    private static final String NAME = "name";

    private static final String OTHER_NAME = "other name";


    private Status underTest;

    @Test
    public void testConstructor() {
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

}

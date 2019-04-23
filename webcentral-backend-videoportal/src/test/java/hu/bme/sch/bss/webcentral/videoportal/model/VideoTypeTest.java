package hu.bme.sch.bss.webcentral.videoportal.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Péter Veress
 */

class VideoTypeTest {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    private static final String LONG_NAME = "long name";
    private static final String CANONICAL_NAME = "canonical-name";
    private static final String DESCRIPTION = "description";

    private VideoType underTest;

    @BeforeEach
    private void init() {
        initMocks(this);
    }

    @Test
    void testConstructor() {
        // GIVEN setup
        
        // WHEN
        underTest = getDefaultValuesBuilder().build();
        
        // THEN
        assertAll(
            () -> assertEquals(CANONICAL_NAME, underTest.getCanonicalName()),
            () -> assertEquals(LONG_NAME, underTest.getLongName()),
            () -> assertEquals(DESCRIPTION, underTest.getDescription()),
            () -> assertFalse(underTest.getArchived())
        );
    }

    @Test
    void testValidationShouldFailForMissingLongName() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .withLongName(null)
            .build();

        // WHEN
        Set<ConstraintViolation<VideoType>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "longName");
    }

    @Test
    void testValidationShouldFailForEmptyLongName() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .withLongName("")
            .build();

        // WHEN
        Set<ConstraintViolation<VideoType>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "longName");
    }

    @Test
    void testValidationShouldFailForMissingCanonicalName() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .withCanonicalName(null)
            .build();

        // WHEN
        Set<ConstraintViolation<VideoType>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "canonicalName");
    }

    @Test
    void testValidationShouldFailForEmptyCanonicalName() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .withCanonicalName("")
            .build();

        // WHEN
        Set<ConstraintViolation<VideoType>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "canonicalName");
    }

    @Test
    void testValidationShouldFailForMissingDescription() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .withDescription(null)
            .build();

        // WHEN
        Set<ConstraintViolation<VideoType>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "description");
    }

    @Test
    void testValidationShouldFailForEmptyDescription() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .withDescription("")
            .build();

        // WHEN
        Set<ConstraintViolation<VideoType>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "description");
    }
    
    private VideoType.Builder getDefaultValuesBuilder(){
        return VideoType.builder()
            .withCanonicalName(CANONICAL_NAME)
            .withLongName(LONG_NAME)
            .withDescription(DESCRIPTION);
    }

    private void thenValidationFails(Set<ConstraintViolation<VideoType>> violations, String expectedMessage, String expectedProperty) {
        assertThat(violations.size(), is(1));
        ConstraintViolation<VideoType> violation = violations.stream().findFirst().get();
        assertThat(violation.getMessage(), is(expectedMessage));
        assertThat(violation.getPropertyPath().toString(), is(expectedProperty));
    }

}

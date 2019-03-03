package hu.bme.sch.bss.webcentral.videoportal.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

class CreateVideoRequestTest {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
            .getValidator();

    private static final String LONG_NAME = "long name";
    private static final String CANONICAL_NAME = "canonical-name";
    private static final String PROJECT_NAME = "projectName";
    private static final String DESCRIPTION = "description";
    private static final Boolean VISIBILITY = true;
    private static final String IMAGE_LOCATION = "image/location";
    private static final String VIDEO_LOCATION = "video/location";

    private CreateVideoRequest underTest;

    @Test
    void testConstructorAndGetters(){
        // GIVEN

        // WHEN
        underTest = getDefaultValuesBuilder()
                .build();

        // THEN
        assertEquals(LONG_NAME, underTest.getLongName());
        assertEquals(CANONICAL_NAME, underTest.getCanonicalName());
        assertEquals(PROJECT_NAME, underTest.getProjectName());
        assertEquals(DESCRIPTION, underTest.getDescription());
        assertEquals(VISIBILITY, underTest.getVisible());
        assertEquals(IMAGE_LOCATION, underTest.getImageLocation());
        assertEquals(VIDEO_LOCATION, underTest.getVideoLocation());
    }

    @Test
    void testValidationShouldFailForMissingLongName() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
                .withLongName(null)
                .build();

        // WHEN
        Set<ConstraintViolation<CreateVideoRequest>> violations = VALIDATOR.validate(underTest);

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
        Set<ConstraintViolation<CreateVideoRequest>> violations = VALIDATOR.validate(underTest);

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
        Set<ConstraintViolation<CreateVideoRequest>> violations = VALIDATOR.validate(underTest);

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
        Set<ConstraintViolation<CreateVideoRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "canonicalName");
    }

    @Test
    void testValidationShouldFailForMissingProjectName() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
                .withProjectName(null)
                .build();

        // WHEN
        Set<ConstraintViolation<CreateVideoRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "projectName");
    }

    @Test
    void testValidationShouldFailForEmptyProjectName() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
                .withProjectName("")
                .build();

        // WHEN
        Set<ConstraintViolation<CreateVideoRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "projectName");
    }

    @Test
    void testValidationShouldFailForMissingDescription() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
                .withDescription(null)
                .build();

        // WHEN
        Set<ConstraintViolation<CreateVideoRequest>> violations = VALIDATOR.validate(underTest);

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
        Set<ConstraintViolation<CreateVideoRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "description");
    }

    @Test
    void testValidationShouldFailForMissingVisibility() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
                .withVisible(null)
                .build();

        // WHEN
        Set<ConstraintViolation<CreateVideoRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be null", "visible");
    }

    @Test
    void testValidationShouldFailForMissingVideoLocation() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
                .withVideoLocation(null)
                .build();

        // WHEN
        Set<ConstraintViolation<CreateVideoRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "videoLocation");
    }

    @Test
    void testValidationShouldFailForEmptyVideoLocation() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
                .withVideoLocation("")
                .build();

        // WHEN
        Set<ConstraintViolation<CreateVideoRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "videoLocation");
    }

    @Test
    void testValidationShouldFailForMissingImageLocation() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
                .withImageLocation(null)
                .build();

        // WHEN
        Set<ConstraintViolation<CreateVideoRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "imageLocation");
    }

    @Test
    void testValidationShouldFailForEmptyImageLocation() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
                .withImageLocation("")
                .build();

        // WHEN
        Set<ConstraintViolation<CreateVideoRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "imageLocation");
    }

    private CreateVideoRequest.Builder getDefaultValuesBuilder() {
        return CreateVideoRequest.builder()
                .withLongName(LONG_NAME)
                .withCanonicalName(CANONICAL_NAME)
                .withDescription(DESCRIPTION)
                .withProjectName(PROJECT_NAME)
                .withVisible(VISIBILITY)
                .withVideoLocation(VIDEO_LOCATION)
                .withImageLocation(IMAGE_LOCATION);
    }

    private void thenValidationFails(Set<ConstraintViolation<CreateVideoRequest>> violations,
                                     String expectedMessage, String expectedProperty) {
        assertThat(violations.size(), is(1));
        ConstraintViolation<CreateVideoRequest> violation = violations.stream().findFirst().get();
        assertThat(violation.getMessage(), is(expectedMessage));
        assertThat(violation.getPropertyPath().toString(), is(expectedProperty));
    }

}
package hu.bme.sch.bss.webcentral.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VideoTest {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
            .getValidator();

    private static final String LONG_NAME = "long name";
    private static final String CANONICAL_NAME = "canonical-name";
    private static final String PROJECT_NAME = "projectName";
    private static final String DESCRIPTION = "description";
    private static final Boolean VISIBILITY = true;
    private static final String IMAGE_LOCATION = "image/location";
    private static final String VIDEO_LOCATION = "video/location";

    private Video underTest;

    @BeforeEach
    public void init() {

    }

    @Test
    public void testValidationShouldFailForMissingLongName() {
        // GIVEN
        underTest = getBuilderWithDefaultValues()
                .withLongName(null)
                .build();

        // WHEN
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "longName");
    }

    @Test
    public void testValidationShouldFailForEmptyLongName() {
        // GIVEN
        underTest = getBuilderWithDefaultValues()
                .withLongName("")
                .build();

        // WHEN
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "longName");
    }

    @Test
    public void testValidationShouldFailForMissingCanonicalName() {
        // GIVEN
        underTest = getBuilderWithDefaultValues()
                .withCanonicalName(null)
                .build();

        // WHEN
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "canonicalName");
    }

    @Test
    public void testValidationShouldFailForEmptyCanonicalName() {
        // GIVEN
        underTest = getBuilderWithDefaultValues()
                .withCanonicalName("")
                .build();

        // WHEN
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "canonicalName");
    }

    @Test
    public void testValidationShouldFailForMissingProjectName() {
        // GIVEN
        underTest = getBuilderWithDefaultValues()
                .withProjectName(null)
                .build();

        // WHEN
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "projectName");
    }

    @Test
    public void testValidationShouldFailForEmptyProjectName() {
        // GIVEN
        underTest = getBuilderWithDefaultValues()
                .withProjectName("")
                .build();

        // WHEN
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "projectName");
    }

    @Test
    public void testValidationShouldFailForMissingDescription() {
        // GIVEN
        underTest = getBuilderWithDefaultValues()
                .withDescription(null)
                .build();

        // WHEN
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "description");
    }

    @Test
    public void testValidationShouldFailForEmptyDescription() {
        // GIVEN
        underTest = getBuilderWithDefaultValues()
                .withDescription("")
                .build();

        // WHEN
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "description");
    }

    @Test
    public void testValidationShouldFailForMissingVisibility() {
        // GIVEN
        underTest = getBuilderWithDefaultValues()
                .withVisible(null)
                .build();

        // WHEN
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be null", "visible");
    }

    @Test
    public void testValidationShouldFailForMissingVideoLocation() {
        // GIVEN
        underTest = getBuilderWithDefaultValues()
                .withVideoLocation(null)
                .build();

        // WHEN
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "videoLocation");
    }

    @Test
    public void testValidationShouldFailForEmptyVideoLocation() {
        // GIVEN
        underTest = getBuilderWithDefaultValues()
                .withVideoLocation("")
                .build();

        // WHEN
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "videoLocation");
    }

    @Test
    public void testValidationShouldFailForMissingImageLocation() {
        // GIVEN
        underTest = getBuilderWithDefaultValues()
                .withImageLocation(null)
                .build();

        // WHEN
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "imageLocation");
    }

    @Test
    public void testValidationShouldFailForEmptyImageLocation() {
        // GIVEN
        underTest = getBuilderWithDefaultValues()
                .withImageLocation("")
                .build();

        // WHEN
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "imageLocation");
    }

    private Video.Builder getBuilderWithDefaultValues() {
        return Video.builder()
                .withLongName(LONG_NAME)
                .withCanonicalName(CANONICAL_NAME)
                .withDescription(DESCRIPTION)
                .withProjectName(PROJECT_NAME)
                .withVisible(VISIBILITY)
                .withVideoLocation(VIDEO_LOCATION)
                .withImageLocation(IMAGE_LOCATION);
    }

    private void thenValidationFails(Set<ConstraintViolation<Video>> violations,
                                     String expectedMessage, String expectedProperty) {
        assertThat(violations.size(), is(1));
        ConstraintViolation<Video> violation = violations.stream().findFirst().get();
        assertThat(violation.getMessage(), is(expectedMessage));
        assertThat(violation.getPropertyPath().toString(), is(expectedProperty));
    }


}

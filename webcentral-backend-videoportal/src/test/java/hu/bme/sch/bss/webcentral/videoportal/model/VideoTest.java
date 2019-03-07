package hu.bme.sch.bss.webcentral.videoportal.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;

class VideoTest {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
            .getValidator();

    private static final String LONG_NAME = "long name";
    private static final String CANONICAL_NAME = "canonical-name";
    private static final String PROJECT_NAME = "projectName";
    private static final String DESCRIPTION = "description";
    private static final Boolean VISIBLE = true;
    private static final boolean UNARCHIVED = false;
    private static final String IMAGE_LOCATION = "image/location";
    private static final String VIDEO_LOCATION = "video/location";

    private Video underTest;

    @Test
    void testConstructorAndGetters() {
        // GIVEN

        // WHEN
        underTest = getDefaultValuesBuilder()
                .build();

        // THEN
        assertEquals(LONG_NAME, underTest.getLongName());
        assertEquals(CANONICAL_NAME, underTest.getCanonicalName());
        assertEquals(PROJECT_NAME, underTest.getProjectName());
        assertEquals(DESCRIPTION, underTest.getDescription());
        assertEquals(VISIBLE, underTest.getVisible());
        assertEquals(UNARCHIVED, underTest.getArchived());
        assertEquals(IMAGE_LOCATION, underTest.getImageLocation());
        assertEquals(VIDEO_LOCATION, underTest.getVideoLocation());
    }

    @Test
    void testEquals() {
        // GIVEN
        Video.Builder builder = getDefaultValuesBuilder();

        // WHEN
        Video video1 = builder.build();
        Video video2 = builder.build();
        Video video3 = builder
                .withCanonicalName("más")
                .build();

        // THEN
        assertTrue(video1.equals(video2));
        assertFalse(video1.equals(video3));
    }

    @Test
    void testSetArchivedShouldArchiveVideo() {
        // GIVEN
        Video video = getDefaultValuesBuilder().build();

        // WHEN
        video.setArchived(true);

        // THEN
        assertTrue(video.getArchived());
    }

    @Test
    void testSetArchivedShouldRestoreVideo() {
        // GIVEN
        Video video = getDefaultValuesBuilder().build();

        // WHEN
        video.setArchived(false);

        // THEN
        assertFalse(video.getArchived());
    }

    @Test
    void testValidationShouldFailForMissingLongName() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
                .withLongName(null)
                .build();

        // WHEN
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

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
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

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
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

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
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

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
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

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
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

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
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

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
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

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
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

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
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

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
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

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
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

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
        Set<ConstraintViolation<Video>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "imageLocation");
    }

    private Video.Builder getDefaultValuesBuilder() {
        return Video.builder()
                .withLongName(LONG_NAME)
                .withCanonicalName(CANONICAL_NAME)
                .withDescription(DESCRIPTION)
                .withProjectName(PROJECT_NAME)
                .withVisible(VISIBLE)
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

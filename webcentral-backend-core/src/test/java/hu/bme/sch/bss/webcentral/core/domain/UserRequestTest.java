package hu.bme.sch.bss.webcentral.core.domain;


import hu.bme.sch.bss.webcentral.core.model.Position;
import hu.bme.sch.bss.webcentral.core.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class UserRequestTest {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
            .getValidator();

    private static final Boolean ARCHIVED = false;
    private static final String NICKNAME = "nickname";
    private static final String GIVEN_NAME = "givenName";
    private static final String FAMILY_NAME = "familyName";
    private static final String EMAIL = "email@address.com";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_URI = "image/URI.png";
    private static final String POSITION_NAME = "position";
    private static final String STATUS_NAME = "status";
    private static final Position POSITION;
    private static final Status STATUS;
    static {
        POSITION = Position.builder()
                .withName(POSITION_NAME)
                .build();
        STATUS = Status.builder()
                .withName(STATUS_NAME)
                .build();
    }

    private UserRequest.Builder defaultBuilder;

    private UserRequest underTest;

    @BeforeEach
    void init() {
        defaultBuilder = UserRequest.builder()
                .withArchived(ARCHIVED)
                .withNickname(NICKNAME)
                .withGivenName(GIVEN_NAME)
                .withFamilyName(FAMILY_NAME)
                .withEmail(EMAIL)
                .withDescription(DESCRIPTION)
                .withImageUri(IMAGE_URI)
                .withStatus(STATUS)
                .withPosition(POSITION);
    }

    @Test
    void testConstructorAndGetters() {
        // GIVEN

        // WHEN
        underTest = defaultBuilder
                .build();

        // THEN
        assertEquals(ARCHIVED, underTest.getArchived());
        assertEquals(NICKNAME, underTest.getNickname());
        assertEquals(GIVEN_NAME, underTest.getGivenName());
        assertEquals(FAMILY_NAME, underTest.getFamilyName());
        assertEquals(EMAIL, underTest.getEmail());
        assertEquals(DESCRIPTION, underTest.getDescription());
        assertEquals(IMAGE_URI, underTest.getImageUri());
        assertEquals(STATUS, underTest.getStatus());
        assertEquals(POSITION, underTest.getPosition());
    }

    @Test
    void testValidationShouldFailForNullArchived() {
        //GIVEN
        underTest = defaultBuilder
                .withArchived(null)
                .build();

        // WHEN
        Set<ConstraintViolation<UserRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be null", "archived");
    }

    @Test
    void testValidationShouldFailForEmptyNickname() {
        //GIVEN
        underTest = defaultBuilder
                .withNickname("")
                .build();

        // WHEN
        Set<ConstraintViolation<UserRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "nickname");
    }

    @Test
    void testValidationShouldFailForEmptyGivenName() {
        //GIVEN
        underTest = defaultBuilder
                .withGivenName("")
                .build();

        // WHEN
        Set<ConstraintViolation<UserRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "givenName");
    }

    @Test
    void testValidationShouldFailForEmptyFamilyName() {
        //GIVEN
        underTest = defaultBuilder
                .withFamilyName("")
                .build();

        // WHEN
        Set<ConstraintViolation<UserRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "familyName");
    }

    @Test
    void testValidationShouldFailForEmptyEmail() {
        //GIVEN
        underTest = defaultBuilder
                .withEmail("")
                .build();

        // WHEN
        Set<ConstraintViolation<UserRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "email");
    }

    @Test
    void testValidationShouldFailForEmptyDescription() {
        //GIVEN
        underTest = defaultBuilder
                .withDescription("")
                .build();

        // WHEN
        Set<ConstraintViolation<UserRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "description");
    }

    @Test
    void testValidationShouldFailForNullStatus() {
        //GIVEN
        underTest = defaultBuilder
                .withStatus(null)
                .build();

        // WHEN
        Set<ConstraintViolation<UserRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be null", "status");
    }

    @Test
    void testValidationShouldFailForNullPosition() {
        //GIVEN
        underTest = defaultBuilder
                .withPosition(null)
                .build();

        // WHEN
        Set<ConstraintViolation<UserRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be null", "position");
    }

    private void thenValidationFails(Set<ConstraintViolation<UserRequest>> violations,
                                     String expectedMessage, String expectedProperty) {
        assertThat(violations.size(), is(1));
        ConstraintViolation<UserRequest> violation = violations.stream().findFirst().get();
        assertThat(violation.getMessage(), is(expectedMessage));
        assertThat(violation.getPropertyPath().toString(), is(expectedProperty));
    }

}

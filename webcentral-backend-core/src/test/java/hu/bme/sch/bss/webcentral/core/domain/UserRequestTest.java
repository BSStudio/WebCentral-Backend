package hu.bme.sch.bss.webcentral.core.domain;


import hu.bme.sch.bss.webcentral.core.model.Position;
import hu.bme.sch.bss.webcentral.core.model.Status;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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

    private UserRequest underTest;

    @Mock
    private Position mockPosition;
    @Mock
    private Status mockStatus;

    @Test
    void testConstructorAndGetters() {
        // GIVEN

        // WHEN
        underTest = UserRequest.builder()
                .withArchived(ARCHIVED)
                .withNickname(NICKNAME)
                .withGivenName(GIVEN_NAME)
                .withFamilyName(FAMILY_NAME)
                .withEmail(EMAIL)
                .withDescription(DESCRIPTION)
                .withImageUri(IMAGE_URI)
                .withStatus(mockStatus)
                .withPosition(mockPosition)
                .build();

        // THEN
        assertEquals(ARCHIVED, underTest.getArchived());
        assertEquals(NICKNAME, underTest.getNickname());
        assertEquals(GIVEN_NAME, underTest.getGivenName());
        assertEquals(FAMILY_NAME, underTest.getFamilyName());
        assertEquals(EMAIL, underTest.getEmail());
        assertEquals(DESCRIPTION, underTest.getDescription());
        assertEquals(IMAGE_URI, underTest.getImageUri());
        assertEquals(mockStatus, underTest.getStatus());
        assertEquals(mockPosition, underTest.getPosition());
    }

    @Test
    void testValidationShouldFailForNullArchived() {
        //GIVEN
        underTest = UserRequest.builder()
                .withArchived(ARCHIVED)
                .withNickname(NICKNAME)
                .withGivenName(GIVEN_NAME)
                .withFamilyName(FAMILY_NAME)
                .withEmail(EMAIL)
                .withDescription(DESCRIPTION)
                .withImageUri(IMAGE_URI)
                .withStatus(mockStatus)
                .withPosition(mockPosition)
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
        underTest = UserRequest.builder()
                .withArchived(ARCHIVED)
                .withNickname(NICKNAME)
                .withGivenName(GIVEN_NAME)
                .withFamilyName(FAMILY_NAME)
                .withEmail(EMAIL)
                .withDescription(DESCRIPTION)
                .withImageUri(IMAGE_URI)
                .withStatus(mockStatus)
                .withPosition(mockPosition)
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
        underTest = UserRequest.builder()
                .withArchived(ARCHIVED)
                .withNickname(NICKNAME)
                .withGivenName(GIVEN_NAME)
                .withFamilyName(FAMILY_NAME)
                .withEmail(EMAIL)
                .withDescription(DESCRIPTION)
                .withImageUri(IMAGE_URI)
                .withStatus(mockStatus)
                .withPosition(mockPosition)
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
        underTest = UserRequest.builder()
                .withArchived(ARCHIVED)
                .withNickname(NICKNAME)
                .withGivenName(GIVEN_NAME)
                .withFamilyName(FAMILY_NAME)
                .withEmail(EMAIL)
                .withDescription(DESCRIPTION)
                .withImageUri(IMAGE_URI)
                .withStatus(mockStatus)
                .withPosition(mockPosition)
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
        underTest = UserRequest.builder()
                .withArchived(ARCHIVED)
                .withNickname(NICKNAME)
                .withGivenName(GIVEN_NAME)
                .withFamilyName(FAMILY_NAME)
                .withEmail(EMAIL)
                .withDescription(DESCRIPTION)
                .withImageUri(IMAGE_URI)
                .withStatus(mockStatus)
                .withPosition(mockPosition)
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
        underTest = UserRequest.builder()
                .withArchived(ARCHIVED)
                .withNickname(NICKNAME)
                .withGivenName(GIVEN_NAME)
                .withFamilyName(FAMILY_NAME)
                .withEmail(EMAIL)
                .withDescription(DESCRIPTION)
                .withImageUri(IMAGE_URI)
                .withStatus(mockStatus)
                .withPosition(mockPosition)
                .withDescription("")
                .build();

        // WHEN
        Set<ConstraintViolation<UserRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "description");
    }

    @Test
    void testValidationShouldFailForEmptyStatus() {
        //GIVEN
        underTest = UserRequest.builder()
                .withArchived(ARCHIVED)
                .withNickname(NICKNAME)
                .withGivenName(GIVEN_NAME)
                .withFamilyName(FAMILY_NAME)
                .withEmail(EMAIL)
                .withDescription(DESCRIPTION)
                .withImageUri(IMAGE_URI)
                .withStatus(mockStatus)
                .withPosition(mockPosition)
                .withStatus(mockStatus)
                .build();

        // WHEN
        Set<ConstraintViolation<UserRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "status");
    }

    @Test
    void testValidationShouldFailForEmptyPosition() {
        //GIVEN
        underTest = UserRequest.builder()
                .withArchived(ARCHIVED)
                .withNickname(NICKNAME)
                .withGivenName(GIVEN_NAME)
                .withFamilyName(FAMILY_NAME)
                .withEmail(EMAIL)
                .withDescription(DESCRIPTION)
                .withImageUri(IMAGE_URI)
                .withStatus(mockStatus)
                .withPosition(mockPosition)
                .withPosition(mockPosition)
                .build();

        // WHEN
        Set<ConstraintViolation<UserRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "position");
    }

    private void thenValidationFails(Set<ConstraintViolation<UserRequest>> violations,
                                     String expectedMessage, String expectedProperty) {
        assertThat(violations.size(), is(1));
        ConstraintViolation<UserRequest> violation = violations.stream().findFirst().get();
        assertThat(violation.getMessage(), is(expectedMessage));
        assertThat(violation.getPropertyPath().toString(), is(expectedProperty));
    }

}

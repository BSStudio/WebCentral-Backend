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

class UserRequestTest {

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
    private PositionRequest mockPosition;
    @Mock
    private StatusRequest mockStatus;

    @Test
    void testConstructorAndGetters() {
        // GIVEN

        // WHEN
        underTest = getDefaultValuesBuilder()
                .build();

        // THEN
        assertEquals(ARCHIVED, underTest.getArchived());
        assertEquals(NICKNAME, underTest.getNickname());
        assertEquals(GIVEN_NAME, underTest.getGivenName());
        assertEquals(FAMILY_NAME, underTest.getFamilyName());
        assertEquals(EMAIL, underTest.getEmail());
        assertEquals(DESCRIPTION, underTest.getDescription());
        assertEquals(IMAGE_URI, underTest.getImageUri());
        assertEquals(mockStatus, underTest.getStatusRequest());
        assertEquals(mockPosition, underTest.getPositionRequest());
    }

    @Test
    void testValidationShouldFailForNullArchived() {
        //GIVEN
        underTest = getDefaultValuesBuilder()
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
        underTest = getDefaultValuesBuilder()
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
        underTest = getDefaultValuesBuilder()
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
        underTest = getDefaultValuesBuilder()
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
        underTest = getDefaultValuesBuilder()
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
        underTest = getDefaultValuesBuilder()
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
        underTest = getDefaultValuesBuilder()
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
        underTest = getDefaultValuesBuilder()
                .withPosition(mockPosition)
                .build();

        // WHEN
        Set<ConstraintViolation<UserRequest>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "position");
    }

    private UserRequest.Builder getDefaultValuesBuilder() {
        return UserRequest.builder()
                .withArchived(ARCHIVED)
                .withNickname(NICKNAME)
                .withGivenName(GIVEN_NAME)
                .withFamilyName(FAMILY_NAME)
                .withEmail(EMAIL)
                .withDescription(DESCRIPTION)
                .withImageUri(IMAGE_URI)
                .withStatus(mockStatus)
                .withPosition(mockPosition);
    }

    private void thenValidationFails(Set<ConstraintViolation<UserRequest>> violations,
                                     String expectedMessage, String expectedProperty) {
        assertThat(violations.size(), is(1));
        ConstraintViolation<UserRequest> violation = violations.stream().findFirst().get();
        assertThat(violation.getMessage(), is(expectedMessage));
        assertThat(violation.getPropertyPath().toString(), is(expectedProperty));
    }

}

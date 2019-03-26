package hu.bme.sch.bss.webcentral.core.model;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserTest {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
        .getValidator();

    private static final String NICKNAME = "nickname";
    private static final String GIVEN_NAME = "given name";
    private static final String FAMILY_NAME = "family name";
    private static final String EMAIL = "email@address.com";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_URI = "image/uri.png";

    private User underTest;

    @Test
    public void testConstructorAndGetters() {
        // GIVEN

        // WHEN
        underTest = getDefaultValuesBuilder()
            .build();

        // THEN
        assertEquals(NICKNAME, underTest.getNickname());
        assertEquals(GIVEN_NAME, underTest.getGivenName());
        assertEquals(FAMILY_NAME, underTest.getFamilyName());
        assertEquals(EMAIL, underTest.getEmail());
        assertEquals(DESCRIPTION, underTest.getDescription());
        assertEquals(IMAGE_URI, underTest.getImageURI());
    }

    @Test
    public void testEquals() {
        // GIVEN
        User.Builder builder = getDefaultValuesBuilder();

        // WHEN
        User user1 = builder.build();
        User user2 = builder.build();
        User user3 = builder
            .withNickname("something else")
            .build();

        // THEN
        assertEquals(user1, user2);
        assertNotEquals(user1, user3);
        assertNotEquals(user2, user3);
    }

    @Test
    public void testValidationShouldFailForMissingNickname() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .withNickname("")
            .build();

        // WHEN
        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "nickname");
    }

    @Test
    public void testValidationShouldFailForMissingGivenName() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .withGivenName("")
            .build();

        // WHEN
        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "givenName");
    }

    @Test
    public void testValidationShouldFailForMissingFamilyName() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .withFamilyName("")
            .build();

        // WHEN
        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "familyName");
    }

    @Test
    public void testValidationShouldFailForMissingEmail() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .withEmail("")
            .build();

        // WHEN
        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(underTest);

        // THEN
        //TODO
    }

    @Test
    public void testValidationShouldFailForMissingDescription() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .withDescription("")
            .build();

        // WHEN
        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "description");
    }

    private User.Builder getDefaultValuesBuilder() {
        return User.builder()
            .withNickname(NICKNAME)
            .withGivenName(GIVEN_NAME)
            .withFamilyName(FAMILY_NAME)
            .withEmail(EMAIL)
            .withDescription(DESCRIPTION)
            .withImageURI(IMAGE_URI);
    }

    private void thenValidationFails(Set<ConstraintViolation<User>> violations,
                                     String expectedMessage, String expectedProperty) {
        assertThat(violations.size(), is(1));
        ConstraintViolation<User> violation = violations.stream().findFirst().get();
        assertThat(violation.getMessage(), is(expectedMessage));
        assertThat(violation.getPropertyPath().toString(), is(expectedProperty));
    }


}

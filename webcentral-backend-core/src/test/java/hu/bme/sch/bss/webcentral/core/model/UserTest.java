package hu.bme.sch.bss.webcentral.core.model;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserTest {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
        .getValidator();

    private static final String NICKNAME = "nickname";
    private static final String GIVEN_NAME = "given name";
    private static final String FAMILY_NAME = "family name";
    private static final String EMAIL = "email@address.com";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_URI = "image/uri.png";

    private static final String OTHER_NICKNAME = "other nickname";
    private static final String OTHER_GIVEN_NAME = "other given name";
    private static final String OTHER_FAMILY_NAME = "other family name";
    private static final String OTHER_EMAIL = "other@address.com";
    private static final String OTHER_DESCRIPTION = "other description";
    private static final String OTHER_IMAGE_URI = "image/other.png";

    private User underTest;

    @Test
    public void testConstructorAndGetters() {
        // GIVEN

        // WHEN
        underTest = getDefaultValuesBuilder()
            .build();

        // THEN
        assertAll(
            () -> assertEquals(NICKNAME, underTest.getNickname()),
            () -> assertEquals(GIVEN_NAME, underTest.getGivenName()),
            () -> assertEquals(FAMILY_NAME, underTest.getFamilyName()),
            () -> assertEquals(EMAIL, underTest.getEmail()),
            () -> assertEquals(DESCRIPTION, underTest.getDescription()),
            () -> assertEquals(IMAGE_URI, underTest.getImageUri())
        );
    }

    @Test
    public void testNoArgConstructor() {
        // GIVEN

        // WHEN
        underTest = new User();

        // THEN
        assertAll(
            () -> assertNull(underTest.getId()),
            () -> assertNull(underTest.getNickname()),
            () -> assertNull(underTest.getGivenName()),
            () -> assertNull(underTest.getFamilyName()),
            () -> assertNull(underTest.getEmail()),
            () -> assertNull(underTest.getDescription()),
            () -> assertNull(underTest.getImageUri()),
            () -> assertNull(underTest.getCreatedAt()),
            () -> assertNull(underTest.getUpdatedAt())

        );
    }

    @Test
    public void testEqualsAndHash() {
        // GIVEN
        User.Builder builder = getDefaultValuesBuilder();

        // WHEN
        User validUser1 = builder.build();
        User validUser2 = builder.build();
        User invalidUser = builder
            .withNickname("something else")
            .build();

        // THEN
        assertEquals(validUser1, validUser2);
        assertNotEquals(validUser1, invalidUser);
        assertNotEquals(null, validUser1);
        assertNotEquals(validUser2, invalidUser);
        assertEquals(validUser1.hashCode(), validUser2.hashCode());
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
        User user1 = getDefaultValuesBuilder()
            .withEmail("")
            .build();


        // WHEN
        //Set<ConstraintViolation<User>> violations = VALIDATOR.validate(underTest);

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

    @Test
    public void testSetNickname() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .build();

        // WHEN
        underTest.setNickname(OTHER_NICKNAME);

        // THEN
        assertAll(
            () -> assertEquals(OTHER_NICKNAME, underTest.getNickname()),
            () -> assertEquals(GIVEN_NAME, underTest.getGivenName()),
            () -> assertEquals(FAMILY_NAME, underTest.getFamilyName()),
            () -> assertEquals(EMAIL, underTest.getEmail()),
            () -> assertEquals(DESCRIPTION, underTest.getDescription()),
            () -> assertEquals(IMAGE_URI, underTest.getImageUri())
        );
    }

    @Test
    public void testSetGivenName() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .build();

        // WHEN
        underTest.setGivenName(OTHER_GIVEN_NAME);

        // THEN
        assertAll(
            () -> assertEquals(NICKNAME, underTest.getNickname()),
            () -> assertEquals(OTHER_GIVEN_NAME, underTest.getGivenName()),
            () -> assertEquals(FAMILY_NAME, underTest.getFamilyName()),
            () -> assertEquals(EMAIL, underTest.getEmail()),
            () -> assertEquals(DESCRIPTION, underTest.getDescription()),
            () -> assertEquals(IMAGE_URI, underTest.getImageUri())
        );
    }

    @Test
    public void testSetFamilyName() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .build();

        // WHEN
        underTest.setFamilyName(OTHER_FAMILY_NAME);

        // THEN
        assertAll(
            () -> assertEquals(NICKNAME, underTest.getNickname()),
            () -> assertEquals(GIVEN_NAME, underTest.getGivenName()),
            () -> assertEquals(OTHER_FAMILY_NAME, underTest.getFamilyName()),
            () -> assertEquals(EMAIL, underTest.getEmail()),
            () -> assertEquals(DESCRIPTION, underTest.getDescription()),
            () -> assertEquals(IMAGE_URI, underTest.getImageUri())
        );
    }

    @Test
    public void testSetEmail() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .build();

        // WHEN
        underTest.setEmail(OTHER_EMAIL);

        // THEN
        assertAll(
            () -> assertEquals(NICKNAME, underTest.getNickname()),
            () -> assertEquals(GIVEN_NAME, underTest.getGivenName()),
            () -> assertEquals(FAMILY_NAME, underTest.getFamilyName()),
            () -> assertEquals(OTHER_EMAIL, underTest.getEmail()),
            () -> assertEquals(DESCRIPTION, underTest.getDescription()),
            () -> assertEquals(IMAGE_URI, underTest.getImageUri())
        );
    }

    @Test
    public void testSetDescription() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .build();

        // WHEN
        underTest.setDescription(OTHER_DESCRIPTION);

        // THEN
        assertAll(
            () -> assertEquals(NICKNAME, underTest.getNickname()),
            () -> assertEquals(GIVEN_NAME, underTest.getGivenName()),
            () -> assertEquals(FAMILY_NAME, underTest.getFamilyName()),
            () -> assertEquals(EMAIL, underTest.getEmail()),
            () -> assertEquals(OTHER_DESCRIPTION, underTest.getDescription()),
            () -> assertEquals(IMAGE_URI, underTest.getImageUri())
        );
    }

    @Test
    public void testSetImageUri() {
        // GIVEN
        underTest = getDefaultValuesBuilder()
            .build();

        // WHEN
        underTest.setImageUri(OTHER_IMAGE_URI);

        // THEN
        assertAll(
            () -> assertEquals(NICKNAME, underTest.getNickname()),
            () -> assertEquals(GIVEN_NAME, underTest.getGivenName()),
            () -> assertEquals(FAMILY_NAME, underTest.getFamilyName()),
            () -> assertEquals(EMAIL, underTest.getEmail()),
            () -> assertEquals(DESCRIPTION, underTest.getDescription()),
            () -> assertEquals(OTHER_IMAGE_URI, underTest.getImageUri())
        );
    }


    private User.Builder getDefaultValuesBuilder() {
        return User.builder()
            .withNickname(NICKNAME)
            .withGivenName(GIVEN_NAME)
            .withFamilyName(FAMILY_NAME)
            .withEmail(EMAIL)
            .withDescription(DESCRIPTION)
            .withImageUri(IMAGE_URI);
    }

    private void thenValidationFails(Set<ConstraintViolation<User>> violations,
                                     String expectedMessage, String expectedProperty) {
        assertThat(violations.size(), is(1));
        ConstraintViolation<User> violation = violations.stream().findFirst().get();
        assertThat(violation.getMessage(), is(expectedMessage));
        assertThat(violation.getPropertyPath().toString(), is(expectedProperty));
    }


}

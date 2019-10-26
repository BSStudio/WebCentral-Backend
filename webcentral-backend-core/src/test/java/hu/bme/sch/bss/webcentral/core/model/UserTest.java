package hu.bme.sch.bss.webcentral.core.model;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static hu.bme.sch.bss.webcentral.WcTestUtil.thenValidationFails;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

final class UserTest {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory()
            .getValidator();

    private static final Boolean ARCHIVED = false;
    private static final String NICKNAME = "nickname";
    private static final String GIVEN_NAME = "given name";
    private static final String FAMILY_NAME = "family name";
    private static final String EMAIL = "email@address.com";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_URI = "image/uri.png";
    private static final String STATUS = "status";
    private static final String POSITION = "position";

    private static final Boolean OTHER_ARCHIVED = true;
    private static final String OTHER_NICKNAME = "other nickname";
    private static final String OTHER_GIVEN_NAME = "other given name";
    private static final String OTHER_FAMILY_NAME = "other family name";
    private static final String OTHER_EMAIL = "other@address.com";
    private static final String OTHER_DESCRIPTION = "other description";
    private static final String OTHER_IMAGE_URI = "image/other.png";
    private static final String OTHER_STATUS = "other status";
    private static final String OTHER_POSITION = "other position";

    private static final String TO_STRING_RESULT = "User{id=null, archived=false, nickname='nickname', givenName='given name', familyName='family name', email='email@address.com', description='description', imageUri='image/uri.png', status=Status{id=null, name='status'}, position=Position{id=null, name='position'}}";


    private User underTest;
    private Status status = Status.builder()
            .withName(STATUS)
            .build();
    private Position position = Position.builder()
            .withName(POSITION)
            .build();
    private User.Builder defaultBuilder = User.builder()
            .withArchived(ARCHIVED)
            .withNickname(NICKNAME)
            .withGivenName(GIVEN_NAME)
            .withFamilyName(FAMILY_NAME)
            .withEmail(EMAIL)
            .withDescription(DESCRIPTION)
            .withImageUri(IMAGE_URI)
            .withStatus(status)
            .withPosition(position);

    @Test
    void testConstructorAndGetters() {
        // GIVEN

        // WHEN
        underTest = defaultBuilder
                .build();

        // THEN
        assertAll(
                () -> assertEquals(ARCHIVED, underTest.getArchived()),
                () -> assertEquals(NICKNAME, underTest.getNickname()),
                () -> assertEquals(GIVEN_NAME, underTest.getGivenName()),
                () -> assertEquals(FAMILY_NAME, underTest.getFamilyName()),
                () -> assertEquals(EMAIL, underTest.getEmail()),
                () -> assertEquals(DESCRIPTION, underTest.getDescription()),
                () -> assertEquals(IMAGE_URI, underTest.getImageUri())
        );
    }

    @Test
    void testNoArgConstructor() {
        // GIVEN

        // WHEN
        underTest = new User();

        // THEN
        assertAll(
                () -> assertNull(underTest.getId()),
                () -> assertNull(underTest.getArchived()),
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
    void testValidationShouldFailForMissingNickname() {
        // GIVEN
        underTest = defaultBuilder
                .withNickname("")
                .build();

        // WHEN
        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "nickname");
    }

    @Test
    void testValidationShouldFailForMissingGivenName() {
        // GIVEN
        underTest = defaultBuilder
                .withGivenName("")
                .build();

        // WHEN
        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "givenName");
    }

    @Test
    void testValidationShouldFailForMissingFamilyName() {
        // GIVEN
        underTest = defaultBuilder
                .withFamilyName("")
                .build();

        // WHEN
        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "familyName");
    }

    @Test
    void testValidationShouldFailForNotWellFormedEmailAddress() {
        // GIVEN
        underTest = defaultBuilder
                .withEmail("asd")
                .build();


        // WHEN
        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must be a well-formed email address", "email");
    }

    @Test
    void testValidationShouldFailForNullEmail() {
        // GIVEN
        underTest = defaultBuilder
                .withEmail(null)
                .build();


        // WHEN
        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "email");
    }

    @Test
    void testValidationShouldFailForBlankEmail() {
        // GIVEN
        underTest = defaultBuilder
                .withEmail("")
                .build();


        // WHEN
        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "email");
    }

    @Test
    void testValidationShouldFailForMissingDescription() {
        // GIVEN
        underTest = defaultBuilder
                .withDescription("")
                .build();

        // WHEN
        Set<ConstraintViolation<User>> violations = VALIDATOR.validate(underTest);

        // THEN
        thenValidationFails(violations, "must not be blank", "description");
    }


    @Test
    void testSetArchived() {
        // GIVEN
        underTest = defaultBuilder
                .build();

        // WHEN
        underTest.setArchived(OTHER_ARCHIVED);


        // THEN
        assertAll(
                () -> assertEquals(OTHER_ARCHIVED, underTest.getArchived()),
                () -> assertEquals(NICKNAME, underTest.getNickname()),
                () -> assertEquals(GIVEN_NAME, underTest.getGivenName()),
                () -> assertEquals(FAMILY_NAME, underTest.getFamilyName()),
                () -> assertEquals(EMAIL, underTest.getEmail()),
                () -> assertEquals(DESCRIPTION, underTest.getDescription()),
                () -> assertEquals(IMAGE_URI, underTest.getImageUri()),
                () -> assertEquals(STATUS, underTest.getStatus().getName()),
                () -> assertEquals(POSITION, underTest.getPosition().getName())
        );
    }

    @Test
    void testSetNickname() {
        // GIVEN
        underTest = defaultBuilder
                .build();

        // WHEN
        underTest.setNickname(OTHER_NICKNAME);

        // THEN
        assertAll(
                () -> assertEquals(ARCHIVED, underTest.getArchived()),
                () -> assertEquals(OTHER_NICKNAME, underTest.getNickname()),
                () -> assertEquals(GIVEN_NAME, underTest.getGivenName()),
                () -> assertEquals(FAMILY_NAME, underTest.getFamilyName()),
                () -> assertEquals(EMAIL, underTest.getEmail()),
                () -> assertEquals(DESCRIPTION, underTest.getDescription()),
                () -> assertEquals(IMAGE_URI, underTest.getImageUri()),
                () -> assertEquals(STATUS, underTest.getStatus().getName()),
                () -> assertEquals(POSITION, underTest.getPosition().getName())
        );
    }

    @Test
    void testSetGivenName() {
        // GIVEN
        underTest = defaultBuilder
                .build();

        // WHEN
        underTest.setGivenName(OTHER_GIVEN_NAME);

        // THEN
        assertAll(
                () -> assertEquals(ARCHIVED, underTest.getArchived()),
                () -> assertEquals(NICKNAME, underTest.getNickname()),
                () -> assertEquals(OTHER_GIVEN_NAME, underTest.getGivenName()),
                () -> assertEquals(FAMILY_NAME, underTest.getFamilyName()),
                () -> assertEquals(EMAIL, underTest.getEmail()),
                () -> assertEquals(DESCRIPTION, underTest.getDescription()),
                () -> assertEquals(IMAGE_URI, underTest.getImageUri()),
                () -> assertEquals(STATUS, underTest.getStatus().getName()),
                () -> assertEquals(POSITION, underTest.getPosition().getName())
        );
    }

    @Test
    void testSetFamilyName() {
        // GIVEN
        underTest = defaultBuilder
                .build();

        // WHEN
        underTest.setFamilyName(OTHER_FAMILY_NAME);

        // THEN
        assertAll(
                () -> assertEquals(ARCHIVED, underTest.getArchived()),
                () -> assertEquals(NICKNAME, underTest.getNickname()),
                () -> assertEquals(GIVEN_NAME, underTest.getGivenName()),
                () -> assertEquals(OTHER_FAMILY_NAME, underTest.getFamilyName()),
                () -> assertEquals(EMAIL, underTest.getEmail()),
                () -> assertEquals(DESCRIPTION, underTest.getDescription()),
                () -> assertEquals(IMAGE_URI, underTest.getImageUri()),
                () -> assertEquals(STATUS, underTest.getStatus().getName()),
                () -> assertEquals(POSITION, underTest.getPosition().getName())
        );
    }

    @Test
    void testSetEmail() {
        // GIVEN
        underTest = defaultBuilder
                .build();

        // WHEN
        underTest.setEmail(OTHER_EMAIL);

        // THEN
        assertAll(
                () -> assertEquals(ARCHIVED, underTest.getArchived()),
                () -> assertEquals(NICKNAME, underTest.getNickname()),
                () -> assertEquals(GIVEN_NAME, underTest.getGivenName()),
                () -> assertEquals(FAMILY_NAME, underTest.getFamilyName()),
                () -> assertEquals(OTHER_EMAIL, underTest.getEmail()),
                () -> assertEquals(DESCRIPTION, underTest.getDescription()),
                () -> assertEquals(IMAGE_URI, underTest.getImageUri()),
                () -> assertEquals(STATUS, underTest.getStatus().getName()),
                () -> assertEquals(POSITION, underTest.getPosition().getName())
        );
    }

    @Test
    void testSetDescription() {
        // GIVEN
        underTest = defaultBuilder
                .build();

        // WHEN
        underTest.setDescription(OTHER_DESCRIPTION);

        // THEN
        assertAll(
                () -> assertEquals(ARCHIVED, underTest.getArchived()),
                () -> assertEquals(NICKNAME, underTest.getNickname()),
                () -> assertEquals(GIVEN_NAME, underTest.getGivenName()),
                () -> assertEquals(FAMILY_NAME, underTest.getFamilyName()),
                () -> assertEquals(EMAIL, underTest.getEmail()),
                () -> assertEquals(OTHER_DESCRIPTION, underTest.getDescription()),
                () -> assertEquals(IMAGE_URI, underTest.getImageUri()),
                () -> assertEquals(STATUS, underTest.getStatus().getName()),
                () -> assertEquals(POSITION, underTest.getPosition().getName())
        );
    }

    @Test
    void testSetImageUri() {
        // GIVEN
        underTest = defaultBuilder
                .build();

        // WHEN
        underTest.setImageUri(OTHER_IMAGE_URI);

        // THEN
        assertAll(
                () -> assertEquals(ARCHIVED, underTest.getArchived()),
                () -> assertEquals(NICKNAME, underTest.getNickname()),
                () -> assertEquals(GIVEN_NAME, underTest.getGivenName()),
                () -> assertEquals(FAMILY_NAME, underTest.getFamilyName()),
                () -> assertEquals(EMAIL, underTest.getEmail()),
                () -> assertEquals(DESCRIPTION, underTest.getDescription()),
                () -> assertEquals(OTHER_IMAGE_URI, underTest.getImageUri()),
                () -> assertEquals(STATUS, underTest.getStatus().getName()),
                () -> assertEquals(POSITION, underTest.getPosition().getName())
        );
    }

    @Test
    void testSetStatus() {
        // GIVEN
        underTest = defaultBuilder
                .build();

        // WHEN
        underTest.setStatus(
                Status.builder()
                        .withName(OTHER_STATUS)
                        .build()
        );

        // THEN
        assertAll(
                () -> assertEquals(ARCHIVED, underTest.getArchived()),
                () -> assertEquals(NICKNAME, underTest.getNickname()),
                () -> assertEquals(GIVEN_NAME, underTest.getGivenName()),
                () -> assertEquals(FAMILY_NAME, underTest.getFamilyName()),
                () -> assertEquals(EMAIL, underTest.getEmail()),
                () -> assertEquals(DESCRIPTION, underTest.getDescription()),
                () -> assertEquals(IMAGE_URI, underTest.getImageUri()),
                () -> assertEquals(OTHER_STATUS, underTest.getStatus().getName()),
                () -> assertEquals(POSITION, underTest.getPosition().getName())
        );
    }

    @Test
    void testSetPosition() {
        // GIVEN
        underTest = defaultBuilder
                .build();

        // WHEN
        underTest.setPosition(
                Position.builder()
                        .withName(OTHER_POSITION)
                        .build()
        );

        // THEN
        assertAll(
                () -> assertEquals(ARCHIVED, underTest.getArchived()),
                () -> assertEquals(NICKNAME, underTest.getNickname()),
                () -> assertEquals(GIVEN_NAME, underTest.getGivenName()),
                () -> assertEquals(FAMILY_NAME, underTest.getFamilyName()),
                () -> assertEquals(EMAIL, underTest.getEmail()),
                () -> assertEquals(DESCRIPTION, underTest.getDescription()),
                () -> assertEquals(IMAGE_URI, underTest.getImageUri()),
                () -> assertEquals(STATUS, underTest.getStatus().getName()),
                () -> assertEquals(OTHER_POSITION, underTest.getPosition().getName())
        );
    }
}

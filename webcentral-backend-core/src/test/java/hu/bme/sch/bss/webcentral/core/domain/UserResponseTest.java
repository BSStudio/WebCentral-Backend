package hu.bme.sch.bss.webcentral.core.domain;

import hu.bme.sch.bss.webcentral.core.model.Position;
import hu.bme.sch.bss.webcentral.core.model.Status;
import hu.bme.sch.bss.webcentral.core.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class UserResponseTest {

    private static final Boolean ARCHIVED = false;
    private static final String NICKNAME = "nickname";
    private static final String GIVEN_NAME = "given name";
    private static final String FAMILY_NAME = "family name";
    private static final String EMAIL = "email@address.com";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_URI = "image/uri.png";
    private static final String STATUS_NAME = "status";
    private static final String POSITION_NAME = "position";
    private static final Status STATUS = Status.builder()
            .withName(STATUS_NAME)
            .build();
    private static final Position POSITION = Position.builder()
            .withName(POSITION_NAME)
            .build();

    private UserResponse underTest;


    @Test
    void testConstructorAndGetters() {
        // GIVEN
        User actual = User.builder()
                .withArchived(ARCHIVED)
                .withNickname(NICKNAME)
                .withGivenName(GIVEN_NAME)
                .withFamilyName(FAMILY_NAME)
                .withEmail(EMAIL)
                .withDescription(DESCRIPTION)
                .withImageUri(IMAGE_URI)
                .withStatus(STATUS)
                .withPosition(POSITION)
                .build();

        // WHEN
        underTest = new UserResponse(actual);

        // THEN
        assertAll(
                () -> assertEquals(actual.getId(), underTest.getId()),
                () -> assertEquals(actual.getArchived(), underTest.getArchived()),
                () -> assertEquals(actual.getNickname(), underTest.getNickname()),
                () -> assertEquals(actual.getGivenName(), underTest.getGivenName()),
                () -> assertEquals(actual.getFamilyName(), underTest.getFamilyName()),
                () -> assertEquals(actual.getEmail(), underTest.getEmail()),
                () -> assertEquals(actual.getDescription(), underTest.getDescription()),
                () -> assertEquals(actual.getImageUri(), underTest.getImageUri()),
                () -> assertEquals(actual.getStatus(), underTest.getStatus()),
                () -> assertEquals(actual.getPosition(), underTest.getPosition())
        );
    }
}

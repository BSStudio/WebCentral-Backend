package hu.bme.sch.bss.webcentral.core.domain;

import hu.bme.sch.bss.webcentral.core.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserResponseTest {

    private static final Boolean ARCHIVED = false;
    private static final String NICKNAME = "nickname";
    private static final String GIVEN_NAME = "given name";
    private static final String FAMILY_NAME = "family name";
    private static final String EMAIL = "email@address.com";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_URI = "image/uri.png";


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
            .build();

        // WHEN
        UserResponse response = new UserResponse(actual);

        // THEN
        assertAll(
            () -> assertEquals(actual.getId(), response.getId()),
            () -> assertEquals(actual.getArchived(), response.getArchived()),
            () -> assertEquals(actual.getNickname(), response.getNickname()),
            () -> assertEquals(actual.getGivenName(), response.getGivenName()),
            () -> assertEquals(actual.getFamilyName(), response.getFamilyName()),
            () -> assertEquals(actual.getEmail(), response.getEmail()),
            () -> assertEquals(actual.getDescription(), response.getDescription()),
            () -> assertEquals(actual.getImageUri(), response.getImageUri())
        );
    }
}

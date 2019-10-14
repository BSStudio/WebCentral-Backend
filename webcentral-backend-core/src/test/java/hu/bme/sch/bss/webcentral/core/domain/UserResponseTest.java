package hu.bme.sch.bss.webcentral.core.domain;

import hu.bme.sch.bss.webcentral.core.model.Position;
import hu.bme.sch.bss.webcentral.core.model.Status;
import hu.bme.sch.bss.webcentral.core.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

//TODO
final class UserResponseTest {

    private static final Boolean ARCHIVED = false;
    private static final String NICKNAME = "nickname";
    private static final String GIVEN_NAME = "given name";
    private static final String FAMILY_NAME = "family name";
    private static final String EMAIL = "email@address.com";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_URI = "image/uri.png";
    private static final String STATUS = "status";
    private static final String POSITION = "position";


    @Test
    void testConstructorAndGetters() {
        // GIVEN
        Status mockStatus = mock(Status.class);
        Position mockPosition = mock(Position.class);
        User actual = User.builder()
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
        given(mockStatus.getName()).willReturn(STATUS);
        given(mockPosition.getName()).willReturn(POSITION);

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
                () -> assertEquals(actual.getImageUri(), response.getImageUri()),
                () -> assertEquals(actual.getStatus().getName(), response.getStatus()),
                () -> assertEquals(actual.getPosition().getName(), response.getPosition())
        );
    }

}

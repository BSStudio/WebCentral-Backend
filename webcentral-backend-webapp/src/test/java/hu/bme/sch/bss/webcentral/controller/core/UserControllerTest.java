package hu.bme.sch.bss.webcentral.controller.core;

import hu.bme.sch.bss.webcentral.core.domain.UserRequest;
import hu.bme.sch.bss.webcentral.core.domain.UserResponse;
import hu.bme.sch.bss.webcentral.core.model.User;
import hu.bme.sch.bss.webcentral.core.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserControllerTest {

    private static final String NICKNAME = "nickname";
    private static final String GIVEN_NAME = "Given_name";
    private static final String FAMILY_NAME = "Family_name";
    private static final String EMAIL = "email@email.com";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_URI = "/images/profile.png";

    private UserController underTest;
    private User user;

    @Mock
    private UserService mockUserService;
    @Mock
    private Logger mockLogger;

    @BeforeEach
    void init() {
        initMocks(this);
        underTest = new UserController(mockUserService, mockLogger);
        user = User.builder()
            .withNickname(NICKNAME)
            .withFamilyName(FAMILY_NAME)
            .withGivenName(GIVEN_NAME)
            .withEmail(EMAIL)
            .withDescription(DESCRIPTION)
            .withImageUri(IMAGE_URI)
            .build();
    }

    @Test
    void testCreateUser() {
        //GIVEN
        UserRequest request = UserRequest.builder()
            .withNickname(NICKNAME)
            .withFamilyName(FAMILY_NAME)
            .withGivenName(GIVEN_NAME)
            .withEmail(EMAIL)
            .withDescription(DESCRIPTION)
            .withImageUri(IMAGE_URI)
            .build();
        given(mockUserService.create(request)).willReturn(user);

        //WHEN
        UserResponse response = underTest.createUser(request);

        //THEN
        assertAll(
            () -> assertEquals(request.getNickname(), response.getNickname()),
            () -> assertEquals(request.getFamilyName(), response.getFamilyName()),
            () -> assertEquals(request.getGivenName(), response.getGivenName()),
            () -> assertEquals(request.getEmail(), response.getEmail()),
            () -> assertEquals(request.getDescription(), response.getDescription()),
            () -> assertEquals(request.getImageUri(), response.getImageUri())
        );
    }
}

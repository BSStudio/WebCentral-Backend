package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.UserDao;
import hu.bme.sch.bss.webcentral.core.domain.UserRequest;
import hu.bme.sch.bss.webcentral.core.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.MockitoAnnotations.initMocks;

class UserServiceTest {

    private static final String NICKNAME = "nickname";
    private static final String GIVEN_NAME = "Given_name";
    private static final String FAMILY_NAME = "Family_name";
    private static final String EMAIL = "email@email.com";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_URI = "/images/profile.png";

    private static final String OTHER_NICKNAME = "other nickname";
    private static final String OTHER_GIVEN_NAME = "other GivenName";
    private static final String OTHER_FAMILY_NAME = "otherFamilyName";
    private static final String OTHER_EMAIL = "otheremail@email.com";
    private static final String OTHER_DESCRIPTION = "other description";
    private static final String OTHER_IMAGE_URI = "other/images/profile.png";

    @Mock
    private UserRequest mockUserRequest;
    @Mock
    private Logger mockLogger;
    @Mock
    private UserDao mockUserDao;

    private User user;
    private UserService underTest;

    @BeforeEach
    void init() {
        initMocks(this);
        underTest = spy(new UserService(mockUserDao, mockLogger));

        given(mockUserRequest.getNickname()).willReturn(OTHER_NICKNAME);
        given(mockUserRequest.getGivenName()).willReturn(OTHER_GIVEN_NAME);
        given(mockUserRequest.getFamilyName()).willReturn(OTHER_FAMILY_NAME);
        given(mockUserRequest.getEmail()).willReturn(OTHER_EMAIL);
        given(mockUserRequest.getDescription()).willReturn(OTHER_DESCRIPTION);
        given(mockUserRequest.getImageUri()).willReturn(OTHER_IMAGE_URI);

        user = User.builder()
            .withNickname(NICKNAME)
            .withGivenName(GIVEN_NAME)
            .withFamilyName(FAMILY_NAME)
            .withEmail(EMAIL)
            .withDescription(DESCRIPTION)
            .withImageUri(IMAGE_URI)
            .build();


    }

    @Test
    void testCreateUser() {
        // GIVEN
        doReturn(user).when(underTest).createUserWithRequestData(any());

        // WHEN
        User result = underTest.create(mockUserRequest);

        // THEN
        then(underTest).should().createUserWithRequestData(mockUserRequest);
        then(mockUserDao).should().save(user);

        assertEquals(user, result);
    }

    @Test
    void testCreateUserWithRequestData() {
        // GIVEN setup

        // WHEN
        User result = underTest.createUserWithRequestData(mockUserRequest);

        // THEN
        then(mockUserRequest).should().getNickname();
        then(mockUserRequest).should().getGivenName();
        then(mockUserRequest).should().getFamilyName();
        then(mockUserRequest).should().getEmail();
        then(mockUserRequest).should().getDescription();
        then(mockUserRequest).should().getImageUri();

        assertAll(
            () -> assertEquals(OTHER_NICKNAME, result.getNickname()),
            () -> assertEquals(OTHER_GIVEN_NAME, result.getGivenName()),
            () -> assertEquals(OTHER_FAMILY_NAME, result.getFamilyName()),
            () -> assertEquals(OTHER_EMAIL, result.getEmail()),
            () -> assertEquals(OTHER_DESCRIPTION, result.getDescription()),
            () -> assertEquals(OTHER_IMAGE_URI, result.getImageUri())
        );
    }

    @Test
    void testFindById() {

    }

    @Test
    void testFindByIdWithNoUser() {

    }

    @Test
    void testFindAll() {

    }


}

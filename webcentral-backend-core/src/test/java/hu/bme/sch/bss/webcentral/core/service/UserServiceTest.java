package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.UserDao;
import hu.bme.sch.bss.webcentral.core.domain.UserRequest;
import hu.bme.sch.bss.webcentral.core.model.Position;
import hu.bme.sch.bss.webcentral.core.model.Status;
import hu.bme.sch.bss.webcentral.core.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.initMocks;

class UserServiceTest {

    private static final Long USER_ID = 16L;

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
    private UserDao mockUserDao;
    @Mock
    private Logger mockLogger;
    @Mock
    private UserRequest mockUserRequest;

    private User user;
    @Mock
    private Position mockPosition;
    @Mock
    private Status mockStatus;

    private UserService underTest;

    @BeforeEach
    void init() {
        initMocks(this);
        underTest = new UserService(mockUserDao, mockLogger);

        given(mockUserRequest.getNickname()).willReturn(NICKNAME);
        given(mockUserRequest.getGivenName()).willReturn(GIVEN_NAME);
        given(mockUserRequest.getFamilyName()).willReturn(FAMILY_NAME);
        given(mockUserRequest.getEmail()).willReturn(EMAIL);
        given(mockUserRequest.getDescription()).willReturn(DESCRIPTION);
        given(mockUserRequest.getImageUri()).willReturn(IMAGE_URI);

        user = User.builder()
            .withNickname(NICKNAME)
            .withGivenName(GIVEN_NAME)
            .withFamilyName(FAMILY_NAME)
            .withEmail(EMAIL)
            .withDescription(DESCRIPTION)
            .withImageUri(IMAGE_URI)
            .withStatus(mockStatus)
            .withPosition(mockPosition)
            .build();
    }

    @Test
    void testCreateUser() {
        // GIVEN

        // WHEN
        User result = underTest.create(mockUserRequest, mockStatus, mockPosition);

        // THEN
        then(mockUserDao).should().save(user);


        assertAll(
            () -> assertEquals(NICKNAME, result.getNickname()),
            () -> assertEquals(GIVEN_NAME, result.getGivenName()),
            () -> assertEquals(FAMILY_NAME, result.getFamilyName()),
            () -> assertEquals(EMAIL, result.getEmail()),
            () -> assertEquals(DESCRIPTION, result.getDescription()),
            () -> assertEquals(IMAGE_URI, result.getImageUri()),
            () -> assertEquals(mockStatus, result.getStatus()),
            () -> assertEquals(mockPosition, result.getPosition())
        );
    }

    @Test
    void testFindAll() {
        // GIVEN setup
        List<User> userList = new ArrayList<>();

        User user2 = User.builder()
            .build();

        userList.add(user);
        userList.add(user2);

        given(mockUserDao.findAll()).willReturn(userList);

        // WHEN
        List<User> result = underTest.findAll();

        // THEN
        assertEquals(userList, result);
    }

    @Test
    void testFindArchived() {
        // GIVEN setup
        List<User> archivedList = new ArrayList<>();

        User user2 = User.builder()
            .build();

        archivedList.add(user);
        archivedList.add(user2);

        given(mockUserDao.findAllArchived()).willReturn(archivedList);

        // WHEN
        List<User> result = underTest.findArchived();

        // THEN
        assertEquals(archivedList, result);
    }

    @Test
    void testFindById() {
        // GIVEN setup
        User user = User.builder()
            .build();

        given(mockUserDao.findById(USER_ID)).willReturn(Optional.of(user));

        // WHEN
        User result = underTest.findById(USER_ID);

        // THEN
        assertEquals(user, result);
    }

    @Test
    void testFindByIdShouldThrowNoSuchElementExceptionWhenWrongID() {
        // GIVEN setup
        given(mockUserDao.findById(any())).willReturn(Optional.empty());

        // WHEN
        NoSuchElementException exception = null;
        try {
            underTest.findById(USER_ID);
        } catch (NoSuchElementException e) {
            exception = e;
        }

        // THEN
        assertNotNull(exception);
    }

    @Test
    void testUpdate() {
        // GIVEN setup
        given(mockUserRequest.getNickname()).willReturn(OTHER_NICKNAME);
        given(mockUserRequest.getGivenName()).willReturn(OTHER_GIVEN_NAME);
        given(mockUserRequest.getFamilyName()).willReturn(OTHER_FAMILY_NAME);
        given(mockUserRequest.getEmail()).willReturn(OTHER_EMAIL);
        given(mockUserRequest.getDescription()).willReturn(OTHER_DESCRIPTION);
        given(mockUserRequest.getImageUri()).willReturn(OTHER_IMAGE_URI);


        // WHEN
        underTest.update(mockUserRequest, user);

        // THEN
        then(mockUserRequest).should().getNickname();
        then(mockUserRequest).should().getGivenName();
        then(mockUserRequest).should().getFamilyName();
        then(mockUserRequest).should().getEmail();
        then(mockUserRequest).should().getDescription();
        then(mockUserRequest).should().getImageUri();

        assertAll(
            () -> assertEquals(OTHER_NICKNAME, user.getNickname()),
            () -> assertEquals(OTHER_GIVEN_NAME, user.getGivenName()),
            () -> assertEquals(OTHER_FAMILY_NAME, user.getFamilyName()),
            () -> assertEquals(OTHER_EMAIL, user.getEmail()),
            () -> assertEquals(OTHER_DESCRIPTION, user.getDescription()),
            () -> assertEquals(OTHER_IMAGE_URI, user.getImageUri())
        );
        then(mockUserDao).should().save(user);
    }

    @Test
    void testArchive() {
        // GIVEN setup

        // WHEN
        underTest.archive(user);

        // THEN
        assertTrue(user.getArchived());
        then(mockUserDao).should().save(user);
    }

    @Test
    void testRestore() {
        // GIVEN setup

        // WHEN
        underTest.restore(user);

        // THEN
        assertFalse(user.getArchived());
        then(mockUserDao).should().save(user);
    }

    @Test
    void testDelete() {
        // GIVEN setup

        // WHEN
        underTest.delete(user);

        // THEN
        then(mockUserDao).should().delete(user);
    }

    @Test
    void testUpdateUserStatus() {
        // GIVEN

        // WHEN
        underTest.updateUserStatus(user, mockStatus);

        // THEN
        assertEquals(user.getStatus(), mockStatus);
        then(mockUserDao).should().save(user);
    }

    @Test
    void testUpdateUserPosition() {
        // GIVEN

        // WHEN
        underTest.updateUserPosition(user, mockPosition);

        // THEN
        assertEquals(user.getPosition(), mockPosition);
        then(mockUserDao).should().save(user);
    }
}

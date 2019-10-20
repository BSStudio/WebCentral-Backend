package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.UserDao;
import hu.bme.sch.bss.webcentral.core.domain.UserRequest;
import hu.bme.sch.bss.webcentral.core.model.Position;
import hu.bme.sch.bss.webcentral.core.model.Status;
import hu.bme.sch.bss.webcentral.core.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;

import org.slf4j.Logger;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

final class UserServiceTest {

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
    private static final Position OTHER_POSITION;
    private static final Status OTHER_STATUS;

    static {
        OTHER_POSITION = Position.builder()
                .withName("other position")
                .build();

        OTHER_STATUS = Status.builder()
                .withName("other status")
                .build();
    }

    @Mock
    private UserDao mockUserDao;
    @Mock
    private Logger mockLogger;

    private UserRequest userRequest;
    private User user;
    private Position position;
    private Status status;
    private User.Builder defaultUser;

    private UserService underTest;

    @BeforeEach
    void init() {
        initMocks(this);
        underTest = new UserService(mockUserDao, mockLogger);

        position = Position.builder()
                .withName("position")
                .build();
        status = Status.builder()
                .withName("status")
                .build();
        userRequest = spy(UserRequest.builder()
                .withNickname(NICKNAME)
                .withGivenName(GIVEN_NAME)
                .withFamilyName(FAMILY_NAME)
                .withEmail(EMAIL)
                .withDescription(DESCRIPTION)
                .withImageUri(IMAGE_URI)
                .withStatus(status)
                .withPosition(position)
                .build());
        defaultUser = User.builder()
                .withNickname(NICKNAME)
                .withGivenName(GIVEN_NAME)
                .withFamilyName(FAMILY_NAME)
                .withEmail(EMAIL)
                .withDescription(DESCRIPTION)
                .withImageUri(IMAGE_URI)
                .withStatus(status)
                .withPosition(position);

        user = spy(defaultUser.build());
    }

    @Test
    void testCreateUser() {
        // GIVEN
        doReturn(user).when(mockUserDao).save(any(User.class));

        // WHEN
        final User result = underTest.create(userRequest, status, position);

        // THEN
        assertAll(
                () -> assertNotNull(result),
                () -> assertFalse(result.getArchived()),
                () -> assertEquals(userRequest.getNickname(), result.getNickname()),
                () -> assertEquals(userRequest.getGivenName(), result.getGivenName()),
                () -> assertEquals(userRequest.getFamilyName(), result.getFamilyName()),
                () -> assertEquals(userRequest.getEmail(), result.getEmail()),
                () -> assertEquals(userRequest.getDescription(), result.getDescription()),
                () -> assertEquals(userRequest.getImageUri(), result.getImageUri()),
                () -> assertEquals(userRequest.getStatus(), result.getStatus()),
                () -> assertEquals(userRequest.getPosition(), result.getPosition())
        );
    }

    @Test
    void testUpdate() {
        // GIVEN setup
        userRequest = UserRequest.builder()
                .withNickname(OTHER_NICKNAME)
                .withGivenName(OTHER_GIVEN_NAME)
                .withFamilyName(OTHER_FAMILY_NAME)
                .withEmail(OTHER_EMAIL)
                .withDescription(OTHER_DESCRIPTION)
                .withImageUri(OTHER_IMAGE_URI)
                .withStatus(status)
                .withPosition(position)
                .build();
        final User updatedUser = User.builder()
                .withNickname(OTHER_NICKNAME)
                .withGivenName(OTHER_GIVEN_NAME)
                .withFamilyName(OTHER_FAMILY_NAME)
                .withEmail(OTHER_EMAIL)
                .withDescription(OTHER_DESCRIPTION)
                .withImageUri(OTHER_IMAGE_URI)
                .withStatus(status)
                .withPosition(position)
                .build();
        given(mockUserDao.save(user)).willReturn(updatedUser);


        // WHEN
        final User result = underTest.update(userRequest, user);

        // THEN
        assertAll(
                () -> assertNotNull(result),
                () -> assertFalse(result.getArchived()),
                () -> assertEquals(OTHER_NICKNAME, result.getNickname()),
                () -> assertEquals(OTHER_GIVEN_NAME, result.getGivenName()),
                () -> assertEquals(OTHER_FAMILY_NAME, result.getFamilyName()),
                () -> assertEquals(OTHER_EMAIL, result.getEmail()),
                () -> assertEquals(OTHER_DESCRIPTION, result.getDescription()),
                () -> assertEquals(OTHER_IMAGE_URI, result.getImageUri()),
                () -> assertEquals(status, result.getStatus()),
                () -> assertEquals(position, result.getPosition())
        );
    }

    @Test
    void testArchive() {
        // GIVEN setup
        given(mockUserDao.save(user)).willReturn(user);

        // WHEN
        final User result = underTest.archive(user);

        // THEN
        then(user).should().setArchived(true);
        then(mockUserDao).should().save(user);
        assertAll(
                () -> assertNotNull(result),
                () -> assertTrue(result.getArchived()),
                () -> assertEquals(NICKNAME, result.getNickname()),
                () -> assertEquals(GIVEN_NAME, result.getGivenName()),
                () -> assertEquals(FAMILY_NAME, result.getFamilyName()),
                () -> assertEquals(EMAIL, result.getEmail()),
                () -> assertEquals(DESCRIPTION, result.getDescription()),
                () -> assertEquals(IMAGE_URI, result.getImageUri()),
                () -> assertEquals(status, result.getStatus()),
                () -> assertEquals(position, result.getPosition())
        );
    }

    @Test
    void testRestore() {
        // GIVEN setup
        final User archivedUser = spy(defaultUser
                .withArchived(true)
                .build());
        given(mockUserDao.save(archivedUser)).willReturn(user);

        // WHEN
        final User result = underTest.restore(archivedUser);

        // THEN
        verify(mockUserDao).save(any());
        then(archivedUser).should().setArchived(false);
        assertAll(
                () -> assertNotNull(result),
                () -> assertFalse(result.getArchived())
        );
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
    void testFindById() {
        // GIVEN setup
        final Optional<User> optionalUser = Optional.of(user);
        given(mockUserDao.findById(USER_ID)).willReturn(optionalUser);

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
        final Executable testSubject = () -> underTest.findById(USER_ID);

        // THEN
        assertThrows(NoSuchElementException.class, testSubject);
    }

    @Test
    void testFindAll() {
        // GIVEN setup
        final User user2 = defaultUser.build();
        final List<User> userList = List.of(user, user2);

        given(mockUserDao.findAll()).willReturn(userList);

        // WHEN
        final List<User> result = underTest.findAll();

        // THEN
        assertEquals(userList, result);
    }

    @Test
    void testFindArchived() {
        // GIVEN setup
        final User user2 = defaultUser.build();
        final List<User> archivedList = List.of(user, user2);
        given(mockUserDao.findAllArchived()).willReturn(archivedList);

        // WHEN
        final List<User> result = underTest.findArchived();

        // THEN
        assertEquals(archivedList, result);
    }

    @Test
    void testUpdateUserStatus() {
        // GIVEN
        final User updatedUser = defaultUser
                .withStatus(OTHER_STATUS)
                .build();
        given(mockUserDao.save(user)).willReturn(updatedUser);

        // WHEN
        final User result = underTest.updateUserStatus(user, OTHER_STATUS);

        // THEN
        then(mockUserDao).should().save(user);
        then(user).should().setStatus(OTHER_STATUS);
        assertEquals(result.getStatus(), OTHER_STATUS);
    }

    @Test
    void testUpdateUserPosition() {
        // GIVEN
        final User updatedUser = defaultUser
                .withPosition(OTHER_POSITION)
                .build();
        given(mockUserDao.save(user)).willReturn(updatedUser);

        // WHEN
        final User result = underTest.updateUserPosition(user, OTHER_POSITION);

        // THEN
        then(mockUserDao).should().save(user);
        then(user).should().setPosition(OTHER_POSITION);
        assertEquals(result.getPosition(), OTHER_POSITION);
    }

}

package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.UserDao;
import hu.bme.sch.bss.webcentral.core.domain.UserRequest;
import hu.bme.sch.bss.webcentral.core.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
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
import static org.mockito.Mockito.spy;
import static org.mockito.MockitoAnnotations.initMocks;

class UserServiceTest {

    private static final Long USER_ID = 16L;

    private static final Boolean ARCHIVED = false;
    private static final String NICKNAME = "nickname";
    private static final String GIVEN_NAME = "Given_name";
    private static final String FAMILY_NAME = "Family_name";
    private static final String EMAIL = "email@email.com";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_URI = "/images/profile.png";

    private static final Boolean OTHER_ARCHIVED = true;
    private static final String OTHER_NICKNAME = "other nickname";
    private static final String OTHER_GIVEN_NAME = "other GivenName";
    private static final String OTHER_FAMILY_NAME = "otherFamilyName";
    private static final String OTHER_EMAIL = "otheremail@email.com";
    private static final String OTHER_DESCRIPTION = "other description";
    private static final String OTHER_IMAGE_URI = "other/images/profile.png";

    @Mock
    private Logger mockLogger;
    @Mock
    private UserDao mockUserDao;

    private User user;
    private UserRequest userRequest;
    private UserService underTest;

    @BeforeEach
    void init() {
        initMocks(this);
        underTest = spy(new UserService(mockUserDao, mockLogger));

        userRequest = UserRequest.builder()
                .withNickname(OTHER_NICKNAME)
                .withGivenName(OTHER_GIVEN_NAME)
                .withFamilyName(OTHER_FAMILY_NAME)
                .withEmail(OTHER_EMAIL)
                .withDescription(OTHER_DESCRIPTION)
                .withImageUri(OTHER_IMAGE_URI)
                .withArchived(OTHER_ARCHIVED)
                .build();

        user = User.builder()
                .withArchived(ARCHIVED)
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

        // WHEN
        final User result = underTest.create(userRequest);

        // THEN
        then(mockUserDao).should().save(result);

        assertAll(
                () -> assertEquals(userRequest.getArchived(), result.getArchived()),
                () -> assertEquals(userRequest.getNickname(), result.getNickname()),
                () -> assertEquals(userRequest.getGivenName(), result.getGivenName()),
                () -> assertEquals(userRequest.getFamilyName(), result.getFamilyName()),
                () -> assertEquals(userRequest.getEmail(), result.getEmail()),
                () -> assertEquals(userRequest.getDescription(), result.getDescription()),
                () -> assertEquals(userRequest.getImageUri(), result.getImageUri())
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
    void testFindByIdShouldThrowException() {
        // GIVEN setup
        given(mockUserDao.findById(any())).willReturn(Optional.empty());

        // WHEN
        final Executable testSubject = () -> underTest.findById(USER_ID);

        // THEN
        assertThrows(NoSuchElementException.class, testSubject);
    }

    @Test
    void testUpdate() {
        // GIVEN setup

        // WHEN
        underTest.update(userRequest, user);

        // THEN
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
        user.setArchived(true);

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

}

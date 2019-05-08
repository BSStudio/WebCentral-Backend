package hu.bme.sch.bss.webcentral.controller.core;

import hu.bme.sch.bss.webcentral.core.domain.UserListResponse;
import hu.bme.sch.bss.webcentral.core.domain.UserRequest;
import hu.bme.sch.bss.webcentral.core.domain.UserResponse;
import hu.bme.sch.bss.webcentral.core.model.User;
import hu.bme.sch.bss.webcentral.core.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

class UserControllerTest {

    private static final Long USER_ID = 16L;
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

    @Test
    void testGetUser() {
        User actual = User.builder()
            .withNickname(NICKNAME)
            .withFamilyName(FAMILY_NAME)
            .withGivenName(GIVEN_NAME)
            .withEmail(EMAIL)
            .withDescription(DESCRIPTION)
            .withImageUri(IMAGE_URI)
            .build();

        given(mockUserService.findById(USER_ID)).willReturn(user);

        // WHEN
        UserResponse response = underTest.getUser(USER_ID);

        // THEN
        assertAll(
            () -> assertEquals(actual.getNickname(), response.getNickname()),
            () -> assertEquals(actual.getFamilyName(), response.getFamilyName()),
            () -> assertEquals(actual.getGivenName(), response.getGivenName()),
            () -> assertEquals(actual.getEmail(), response.getEmail()),
            () -> assertEquals(actual.getDescription(), response.getDescription()),
            () -> assertEquals(actual.getImageUri(), response.getImageUri())
        );
    }

    @Test
    void testGetUserShouldThrowNoSuchElementException() {
        // GIVEN
        given(mockUserService.findById(USER_ID)).willThrow(new NoSuchElementException());

        // WHEN
        Exception exception = null;
        UserResponse response = null;
        try {
            response = underTest.getUser(USER_ID);
        } catch (Exception e) {
            exception = e;
        }

        // THEN
        assertNotNull(exception);
        assertNull(response);
        verify(mockUserService).findById(USER_ID);
    }

    @Test
    void testListAllUsers() {
        // GIVEN
        List<User> userList = new ArrayList<>();

        User user2 = User.builder()
            .build();

        userList.add(user);
        userList.add(user2);

        given(mockUserService.findAll()).willReturn(userList);

        // WHEN
        UserListResponse response = underTest.listAllUsers();

        assertEquals(userList, Arrays.asList(response.getUsers()));
    }

    @Test
    void testListArchivedUsers() {
        // GIVEN
        List<User> archivedList = new ArrayList<>();

        User user2 = User.builder()
            .build();

        archivedList.add(user);
        archivedList.add(user2);

        given(mockUserService.findArchived()).willReturn(archivedList);

        // WHEN
        UserListResponse response = underTest.listAllArchived();

        // THEN
        assertEquals(archivedList, Arrays.asList(response.getUsers()));
    }

    @Test
    void testUpdateUser() {
        // GIVEN
        given(mockUserService.findById(USER_ID)).willReturn(user);
        UserRequest request = UserRequest.builder()
            .withNickname(NICKNAME)
            .withFamilyName(FAMILY_NAME)
            .withGivenName(GIVEN_NAME)
            .withEmail(EMAIL)
            .withDescription(DESCRIPTION)
            .withImageUri(IMAGE_URI)
            .build();

        // WHEN
        UserResponse response = underTest.updateUser(USER_ID, request);

        // THEN
        then(mockUserService).should().findById(USER_ID);
        then(mockUserService).should().update(request, user);

        assertAll(
            () -> assertEquals(request.getNickname(), response.getNickname()),
            () -> assertEquals(request.getFamilyName(), response.getFamilyName()),
            () -> assertEquals(request.getGivenName(), response.getGivenName()),
            () -> assertEquals(request.getEmail(), response.getEmail()),
            () -> assertEquals(request.getDescription(), response.getDescription()),
            () -> assertEquals(request.getImageUri(), response.getImageUri())
        );
    }

    @Test
    void testArchiveUser() {
        // GIVEN
        given(mockUserService.findById(USER_ID)).willReturn(user);

        // WHEN
        underTest.archiveUser(USER_ID);

        // THEN
        then(mockUserService).should().findById(USER_ID);
        then(mockUserService).should().archive(user);
    }

    @Test
    void testRestoreUser() {
        // GIVEN
        given(mockUserService.findById(USER_ID)).willReturn(user);

        // WHEN
        underTest.restoreUser(USER_ID);

        // THEN
        then(mockUserService).should().findById(USER_ID);
        then(mockUserService).should().restore(user);
    }

    @Test
    void testDeleteUser() {
        // GIVEN
        given(mockUserService.findById(USER_ID)).willReturn(user);

        // WHEN
        underTest.deleteUser(USER_ID);

        // THEN
        then(mockUserService).should().findById(USER_ID);
        then(mockUserService).should().delete(user);
    }
}

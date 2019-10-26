package hu.bme.sch.bss.webcentral.controller.core;

import hu.bme.sch.bss.webcentral.core.domain.PositionRequest;
import hu.bme.sch.bss.webcentral.core.domain.StatusRequest;
import hu.bme.sch.bss.webcentral.core.domain.UserListResponse;
import hu.bme.sch.bss.webcentral.core.domain.UserRequest;
import hu.bme.sch.bss.webcentral.core.domain.UserResponse;
import hu.bme.sch.bss.webcentral.core.model.Position;
import hu.bme.sch.bss.webcentral.core.model.Status;
import hu.bme.sch.bss.webcentral.core.model.User;
import hu.bme.sch.bss.webcentral.core.service.PositionService;
import hu.bme.sch.bss.webcentral.core.service.StatusService;
import hu.bme.sch.bss.webcentral.core.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import org.mockito.Mock;

import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

final class UserControllerTest {

    private static final Long USER_ID = 16L;
    private static final Boolean ARCHIVED = false;
    private static final String NICKNAME = "nickname";
    private static final String GIVEN_NAME = "Given_name";
    private static final String FAMILY_NAME = "Family_name";
    private static final String EMAIL = "email@email.com";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_URI = "/images/profile.png";
    private static final String STATUS_NAME = "status";
    private static final Long STATUS_ID = 15L;
    private static final String POSITION_NAME = "position";
    private static final Long POSITION_ID = 14L;
    private static final Status STATUS = Status.builder().withName(STATUS_NAME).build();
    private static final Position POSITION = Position.builder().withName(POSITION_NAME).build();

    private UserController underTest;

    private User user;
    private User.Builder defaultBuilder;
    private UserRequest userRequest;
    private StatusRequest statusRequest;
    private PositionRequest positionRequest;

    @Mock
    private UserService mockUserService;
    @Mock
    private PositionService mockPositionService;
    @Mock
    private StatusService mockStatusService;
    @Mock
    private Logger mockLogger;

    @BeforeEach
    void init() {
        initMocks(this);
        underTest = new UserController(mockUserService, mockPositionService, mockStatusService, mockLogger);
        defaultBuilder = User.builder()
                .withArchived(ARCHIVED)
                .withNickname(NICKNAME)
                .withFamilyName(FAMILY_NAME)
                .withGivenName(GIVEN_NAME)
                .withEmail(EMAIL)
                .withDescription(DESCRIPTION)
                .withImageUri(IMAGE_URI)
                .withStatus(STATUS)
                .withPosition(POSITION);
        user = defaultBuilder
                .build();
        userRequest = UserRequest.builder()
                .withArchived(ARCHIVED)
                .withNickname(NICKNAME)
                .withFamilyName(FAMILY_NAME)
                .withGivenName(GIVEN_NAME)
                .withEmail(EMAIL)
                .withDescription(DESCRIPTION)
                .withImageUri(IMAGE_URI)
                .withStatus(STATUS)
                .withPosition(POSITION)
                .build();
        positionRequest = PositionRequest.builder()
                .withName(POSITION_NAME)
                .build();
        statusRequest = StatusRequest.builder()
                .withName(STATUS_NAME)
                .build();
    }

    @Test
    void testCreateUser() {
        //GIVEN
        given(mockStatusService.findByName(STATUS_NAME)).willReturn(STATUS);
        given(mockPositionService.findByName(POSITION_NAME)).willReturn(POSITION);
        given(mockUserService.create(userRequest, STATUS, POSITION)).willReturn(user);

        //WHEN
        final UserResponse response = underTest.createUser(userRequest);

        //THEN
        assertAll(
                () -> assertEquals(userRequest.getArchived(), response.getArchived()),
                () -> assertEquals(userRequest.getNickname(), response.getNickname()),
                () -> assertEquals(userRequest.getFamilyName(), response.getFamilyName()),
                () -> assertEquals(userRequest.getGivenName(), response.getGivenName()),
                () -> assertEquals(userRequest.getEmail(), response.getEmail()),
                () -> assertEquals(userRequest.getDescription(), response.getDescription()),
                () -> assertEquals(userRequest.getImageUri(), response.getImageUri()),
                () -> assertEquals(userRequest.getPosition(), response.getPosition()),
                () -> assertEquals(userRequest.getStatus(), response.getStatus())
        );
    }

    @Test
    void testGetUser() {
        // GIVEN
        given(mockUserService.findById(USER_ID)).willReturn(user);

        // WHEN
        final UserResponse response = underTest.getUser(USER_ID);

        // THEN
        assertAll(
                () -> assertEquals(user.getArchived(), response.getArchived()),
                () -> assertEquals(user.getNickname(), response.getNickname()),
                () -> assertEquals(user.getFamilyName(), response.getFamilyName()),
                () -> assertEquals(user.getGivenName(), response.getGivenName()),
                () -> assertEquals(user.getEmail(), response.getEmail()),
                () -> assertEquals(user.getDescription(), response.getDescription()),
                () -> assertEquals(user.getImageUri(), response.getImageUri()),
                () -> assertEquals(user.getPosition(), response.getPosition()),
                () -> assertEquals(user.getStatus(), response.getStatus())
        );
    }

    @Test
    void testGetUserShouldThrowNoSuchElementException() {
        // GIVEN
        given(mockUserService.findById(USER_ID)).willThrow(new NoSuchElementException());

        // WHEN
        final Executable testSubject = () -> underTest.getUser(USER_ID);

        // THEN
        assertThrows(NoSuchElementException.class, testSubject);
        verify(mockUserService).findById(USER_ID);
    }

    @Test
    void testListAllUsers() {
        // GIVEN
        final User user2 = defaultBuilder.build();
        final List<User> userList = List.of(user, user2);
        given(mockUserService.findAll()).willReturn(userList);

        // WHEN
        final UserListResponse response = underTest.listAllUsers();

        // THEN
        assertArrayEquals(userList.toArray(), response.getUsers());
    }

    @Test
    void testListArchivedUsers() {
        // GIVEN
        final User user2 = defaultBuilder.build();
        final List<User> archivedList = List.of(user, user2);

        given(mockUserService.findArchived()).willReturn(archivedList);

        // WHEN
        final UserListResponse response = underTest.listAllArchived();

        // THEN
        assertEquals(archivedList, Arrays.asList(response.getUsers()));
    }

    @Test
    void testUpdateUser() {
        // GIVEN
        given(mockUserService.findById(USER_ID)).willReturn(user);
        given(mockUserService.update(userRequest, user)).willReturn(user);

        // WHEN
        final UserResponse response = underTest.updateUser(USER_ID, userRequest);

        // THEN
        then(mockUserService).should().findById(USER_ID);
        then(mockUserService).should().update(userRequest, user);

        assertAll(
                () -> assertEquals(userRequest.getArchived(), response.getArchived()),
                () -> assertEquals(userRequest.getNickname(), response.getNickname()),
                () -> assertEquals(userRequest.getFamilyName(), response.getFamilyName()),
                () -> assertEquals(userRequest.getGivenName(), response.getGivenName()),
                () -> assertEquals(userRequest.getEmail(), response.getEmail()),
                () -> assertEquals(userRequest.getDescription(), response.getDescription()),
                () -> assertEquals(userRequest.getImageUri(), response.getImageUri()),
                () -> assertEquals(userRequest.getPosition(), response.getPosition()),
                () -> assertEquals(userRequest.getStatus(), response.getStatus())
        );
    }

    @Test
    void testArchiveUser() {
        // GIVEN
        final User archivedUser = defaultBuilder
                .withArchived(true)
                .build();
        given(mockUserService.findById(USER_ID)).willReturn(user);
        given(mockUserService.archive(user)).willReturn(archivedUser);

        // WHEN
        final UserResponse response = underTest.archiveUser(USER_ID);

        // THEN
        assertTrue(response.getArchived());
        then(mockUserService).should().findById(USER_ID);
        then(mockUserService).should().archive(user);
    }

    @Test
    void testRestoreUser() {
        // GIVEN
        user.setArchived(true);
        final User restoredUser = defaultBuilder
                .withArchived(false)
                .build();
        given(mockUserService.findById(USER_ID)).willReturn(user);
        given(mockUserService.restore(user)).willReturn(restoredUser);

        // WHEN
        final UserResponse response = underTest.restoreUser(USER_ID);

        // THEN
        assertFalse(response.getArchived());
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

    @Test
    void testUpdateUserStatus() {
        // GIVEN
        final Status otherStatus = Status.builder()
                .withName("other")
                .build();
        final User updatedUser = defaultBuilder
                .withStatus(otherStatus)
                .build();
        given(mockUserService.findById(USER_ID)).willReturn(user);
        given(mockStatusService.findByName(STATUS_NAME)).willReturn(otherStatus);
        given(mockUserService.updateUserStatus(user, otherStatus)).willReturn(updatedUser);

        // WHEN
        final UserResponse response = underTest.updateUserStatus(USER_ID, statusRequest);

        // THEN
        assertEquals(otherStatus, response.getStatus());
    }

    @Test
    void testUpdateUserPosition() {
        // GIVEN
        final Position otherPosition = Position.builder()
                .withName("other")
                .build();
        final User updatedUser = defaultBuilder
                .withPosition(otherPosition)
                .build();
        given(mockUserService.findById(USER_ID)).willReturn(user);
        given(mockPositionService.findByName(POSITION_NAME)).willReturn(otherPosition);
        given(mockUserService.updateUserPosition(user, otherPosition)).willReturn(updatedUser);

        // WHEN
        final UserResponse response = underTest.updateUserPosition(USER_ID, positionRequest);

        // THEN
        assertEquals(otherPosition, response.getPosition());
    }

    @Test
    void testListAllWithStatusOf() {
        // GIVEN
        final User user2 = User.builder().build();
        final Set<User> users = Set.of(user, user2);
        given(mockStatusService.findAllUserByStatusId(STATUS_ID)).willReturn(users);

        // WHEN
        final UserListResponse response = underTest.listAllWithStatusOf(STATUS_ID);

        // THEN
        assertArrayEquals(response.getUsers(), users.toArray());
    }

    @Test
    void testListAllWithPositionOf() {
        // GIVEN
        final User user2 = User.builder().build();
        final Set<User> users = Set.of(user, user2);
        given(mockPositionService.findAllUserByPositionId(POSITION_ID)).willReturn(users);

        // WHEN
        final UserListResponse response = underTest.listAllWithPositionOf(POSITION_ID);

        // THEN
        assertArrayEquals(response.getUsers(), users.toArray());
    }

}

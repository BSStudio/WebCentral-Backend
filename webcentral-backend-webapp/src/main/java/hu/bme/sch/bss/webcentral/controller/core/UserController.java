package hu.bme.sch.bss.webcentral.controller.core;

import hu.bme.sch.bss.webcentral.core.domain.UserListResponse;
import hu.bme.sch.bss.webcentral.core.domain.UserRequest;
import hu.bme.sch.bss.webcentral.core.domain.UserResponse;
import hu.bme.sch.bss.webcentral.core.model.User;
import hu.bme.sch.bss.webcentral.core.service.UserService;

import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/user", produces = "application/json")
public class UserController {

    private static final String REQUEST_USER_CREATE = "Request for user creation received. {}";
    private static final String REQUEST_USER_SEARCH = "Request to find user received for id {}";
    private static final String REQUEST_USERS_LIST = "Request to find all non-archived users received.";
    private static final String REQUEST_ARCHIVED_USERS_LIST = "Request to find all archived users received.";
    private static final String REQUEST_USER_UPDATE = "Request to update user received for id {}";
    private static final String REQUEST_USER_RESTORE = "Request to restore user received for id: {}";
    private static final String REQUEST_USER_ARCHIVE = "Request to archive user received for id: {}";
    private static final String REQUEST_USER_DELETE = "Request to delete user reveived for id: {}";
    private final UserService userService;
    private final Logger logger;

    public UserController(final UserService userService, final Logger logger) {
        this.userService = userService;
        this.logger = logger;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public final UserResponse createUser(@Valid @RequestBody final UserRequest request) {
        logger.info(REQUEST_USER_CREATE, request);
        User result = userService.create(request);
        return new UserResponse(result);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final UserResponse getUser(@PathVariable("id") final Long id) {
        logger.info(REQUEST_USER_SEARCH, id);
        User result = userService.findById(id);
        return new UserResponse(result);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public final UserResponse updateUser(@PathVariable("id") final Long id, @RequestBody final UserRequest request) {
        logger.info(REQUEST_USER_UPDATE, id);
        User user = userService.findById(id);
        userService.update(request, user);
        return new UserResponse(user);
    }

    @PutMapping("/{id}/archive")
    @ResponseStatus(HttpStatus.OK)
    public final void archiveUser(@PathVariable("id") final Long id) {
        logger.info(REQUEST_USER_ARCHIVE, id);
        User user = userService.findById(id);
        userService.archive(user);
    }

    @PutMapping("/{id}/restore")
    @ResponseStatus(HttpStatus.OK)
    public final void restoreUser(@PathVariable("id") final Long id) {
        logger.info(REQUEST_USER_RESTORE, id);
        User user = userService.findById(id);
        userService.restore(user);
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public final void deleteUser(@PathVariable("id") final Long id) {
        logger.info(REQUEST_USER_DELETE, id);
        User user = userService.findById(id);
        userService.delete(user);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.FOUND)
    public final UserListResponse listAllUsers() {
        logger.info(REQUEST_USERS_LIST);
        ArrayList<User> users = new ArrayList<>(userService.findAll());
        return UserListResponse.builder()
            .withUsers(users)
            .build();
    }

    @GetMapping("/archived")
    @ResponseStatus(HttpStatus.FOUND)
    public final UserListResponse listAllArchived() {
        logger.info(REQUEST_ARCHIVED_USERS_LIST);
        ArrayList<User> users = new ArrayList<>(userService.findArchived());
        return UserListResponse.builder()
            .withUsers(users)
            .build();
    }

}

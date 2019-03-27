package hu.bme.sch.bss.webcentral.controller.core;

import hu.bme.sch.bss.webcentral.core.domain.UserRequest;
import hu.bme.sch.bss.webcentral.core.domain.UserResponse;
import hu.bme.sch.bss.webcentral.core.model.User;
import hu.bme.sch.bss.webcentral.core.service.UserService;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/core", produces = "application/json")
public class UserController {

    private static final String REQUEST_USER_CREATE = "Request for user creation received. {}";
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

}

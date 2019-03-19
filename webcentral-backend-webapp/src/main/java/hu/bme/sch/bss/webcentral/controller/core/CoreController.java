package hu.bme.sch.bss.webcentral.controller.core;

import hu.bme.sch.bss.webcentral.domain.UserRequest;
import hu.bme.sch.bss.webcentral.domain.UserResponse;
import hu.bme.sch.bss.webcentral.model.User;
import hu.bme.sch.bss.webcentral.service.UserService;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/core", produces = "application/json")
public class CoreController {

    private static final String REQUEST_USER_CREATE = "Request for user creation received. {}";
    private final UserService userService;
    private final Logger logger;

    public CoreController(final UserService userService, final Logger logger) {
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

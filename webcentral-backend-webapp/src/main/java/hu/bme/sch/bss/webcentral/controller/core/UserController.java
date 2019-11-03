package hu.bme.sch.bss.webcentral.controller.core;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.OK;

import hu.bme.sch.bss.webcentral.core.domain.PositionRequest;
import hu.bme.sch.bss.webcentral.core.domain.StatusRequest;
import hu.bme.sch.bss.webcentral.core.domain.UserListResponse;
import hu.bme.sch.bss.webcentral.core.domain.UserRequest;
import hu.bme.sch.bss.webcentral.core.domain.UserResponse;
import hu.bme.sch.bss.webcentral.core.model.Position;
import hu.bme.sch.bss.webcentral.core.model.Status;
import hu.bme.sch.bss.webcentral.core.model.User;
import hu.bme.sch.bss.webcentral.core.service.FileStoreService;
import hu.bme.sch.bss.webcentral.core.service.PositionService;
import hu.bme.sch.bss.webcentral.core.service.StatusService;
import hu.bme.sch.bss.webcentral.core.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public final class UserController {

    private static final String REQUEST_USER_CREATE = "Request for user creation received. {}";
    private static final String REQUEST_USER_SEARCH = "Request to find user received for id {}";
    private static final String REQUEST_USERS_LIST = "Request to find all non-archived users received.";
    private static final String REQUEST_ARCHIVED_USERS_LIST = "Request to find all archived users received.";
    private static final String REQUEST_USER_UPDATE = "Request to update user received for id {}";
    private static final String REQUEST_USER_RESTORE = "Request to restore user received for id: {}";
    private static final String REQUEST_USER_ARCHIVE = "Request to archive user received for id: {}";
    private static final String REQUEST_USER_DELETE = "Request to delete user received for id: {}";
    private static final String REQUEST_USER_UPDATE_STATUS = "Request to update user status requested for id: {}";
    private static final String REQUEST_USER_UPDATE_POSITION = "Request to update user position requested for id: {}";
    private static final String REQUEST_USER_UPDATE_PICTURE = "Request to update user profilePicture requested for id: {}";
    private static final String REQUEST_USERS_WITH_POSITION_OF = "Request users with position of: {}";

    private final UserService userService;
    private final PositionService positionService;
    private final StatusService statusService;
    private final FileStoreService fileStoreService;
    private final Logger logger;

    UserController(final UserService userService,
                   final PositionService positionService,
                   final StatusService statusService,
                   final FileStoreService fileStoreService,
                   final Logger logger) {
        this.userService = userService;
        this.positionService = positionService;
        this.statusService = statusService;
        this.fileStoreService = fileStoreService;
        this.logger = logger;
    }

    @PostMapping()
    @ResponseStatus(CREATED)
    public UserResponse createUser(@Valid @RequestBody final UserRequest request) {
        logger.info(REQUEST_USER_CREATE, request);
        final Status status = statusService.findByName(request.getStatus().getName());
        final Position position = positionService.findByName(request.getPosition().getName());
        final User result = userService.create(request, status, position);
        return new UserResponse(result);
    }

    @GetMapping("/{id}")
    @ResponseStatus(FOUND)
    public UserResponse getUser(@PathVariable("id") final Long id) {
        logger.info(REQUEST_USER_SEARCH, id);
        final User result = userService.findById(id);
        return new UserResponse(result);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public UserResponse updateUser(@PathVariable("id") final Long id, @RequestBody final UserRequest request) {
        logger.info(REQUEST_USER_UPDATE, id);
        final User user = userService.findById(id);
        final User result = userService.update(request, user);
        return new UserResponse(result);
    }

    @PutMapping("/{id}/archive")
    @ResponseStatus(OK)
    public UserResponse archiveUser(@PathVariable("id") final Long id) {
        logger.info(REQUEST_USER_ARCHIVE, id);
        final User user = userService.findById(id);
        final User result = userService.archive(user);
        return new UserResponse(result);
    }

    @PutMapping("/{id}/restore")
    @ResponseStatus(OK)
    public UserResponse restoreUser(@PathVariable("id") final Long id) {
        logger.info(REQUEST_USER_RESTORE, id);
        final User user = userService.findById(id);
        final User result = userService.restore(user);
        return new UserResponse(result);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void deleteUser(@PathVariable("id") final Long id) throws IOException {
        logger.info(REQUEST_USER_DELETE, id);
        final User user = userService.findById(id);
        final String fileName = user.getImageUri();
        userService.delete(user);
        fileStoreService.deleteFileWithNameOf(fileName);
    }

    @PutMapping("/{id}/status")
    @ResponseStatus(OK)
    public UserResponse updateUserStatus(@PathVariable("id") final Long id, @Valid @RequestBody final StatusRequest request) {
        logger.info(REQUEST_USER_UPDATE_STATUS, id);
        final User user = userService.findById(id);
        final Status status = statusService.findByName(request.getName());
        final User result = userService.updateUserStatus(user, status);
        return new UserResponse(result);
    }

    @PutMapping("/{id}/position")
    @ResponseStatus(OK)
    public UserResponse updateUserPosition(@PathVariable("id") final Long id, @Valid @RequestBody final PositionRequest request) {
        logger.info(REQUEST_USER_UPDATE_POSITION, id);
        final User user = userService.findById(id);
        final Position position = positionService.findByName(request.getName());
        final User result = userService.updateUserPosition(user, position);
        return new UserResponse(result);
    }

    @PostMapping("/{id}/image")
    @ResponseStatus(OK)
    public UserResponse updateUserPicture(@PathVariable("id") final Long id,
                                          @RequestParam("picture") final MultipartFile picture) throws IOException {
        logger.info(REQUEST_USER_UPDATE_PICTURE, id);
        final User user = userService.findById(id);
        final String oldName = user.getImageUri();
        final String imageUri = fileStoreService.storeImage(picture);
        fileStoreService.deleteFileWithNameOf(oldName);
        final User result = userService.updateUserPictureUri(user, imageUri);
        return new UserResponse(result);
    }

    @GetMapping("/all")
    @ResponseStatus(FOUND)
    public UserListResponse listAllUsers() {
        logger.info(REQUEST_USERS_LIST);
        final ArrayList<User> users = new ArrayList<>(userService.findAll());
        return UserListResponse.builder()
                .withUsers(users)
                .build();
    }

    @GetMapping("/archived")
    @ResponseStatus(FOUND)
    public UserListResponse listAllArchived() {
        logger.info(REQUEST_ARCHIVED_USERS_LIST);
        final ArrayList<User> users = new ArrayList<>(userService.findArchived());
        return UserListResponse.builder()
                .withUsers(users)
                .build();
    }

    @GetMapping("/status/{id}")
    @ResponseStatus(FOUND)
    public UserListResponse listAllWithStatusOf(@PathVariable("id") final Long id) {
        logger.info(REQUEST_USERS_WITH_POSITION_OF, id);
        final Set<User> users = statusService.findAllUserByStatusId(id);
        return UserListResponse.builder()
                .withUsers(users)
                .build();
    }

    @GetMapping("/position/{id}")
    @ResponseStatus(FOUND)
    public UserListResponse listAllWithPositionOf(@PathVariable("id") final Long id) {
        logger.info(REQUEST_USERS_WITH_POSITION_OF, id);
        final Set<User> users = positionService.findAllUserByPositionId(id);
        return UserListResponse.builder()
                .withUsers(users)
                .build();
    }

}

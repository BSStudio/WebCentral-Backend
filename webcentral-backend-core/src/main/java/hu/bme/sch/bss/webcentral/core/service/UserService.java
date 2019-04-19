package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.UserDao;
import hu.bme.sch.bss.webcentral.core.domain.UserRequest;
import hu.bme.sch.bss.webcentral.core.model.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;


@Component
@SuppressWarnings("designforextension")
public class UserService {

    private static final String USER_CREATE_STARTED = "User creation started. {}";
    private static final String USER_CREATE_SUCCEED = "User creation succeed. {}";
    private static final String USER_SEARCH_STARTED = "User search started. {}";
    private static final String USER_SEARCH_SUCCEED = "User search succeed. {}";
    private static final String USER_NOT_FOUND = "User not found with id {}";
    private static final String USERS_ALL_SEARCH_STARTED = "Search for all users started";
    private static final String USERS_ALL_SEARCH_SUCCEED = "Search for all users succeed";
    private static final String USER_UPDATE_STARTED = "User update started. {}";
    private static final String USER_UPDATE_SUCCEED = "User update succeed. {}";
    private static final String USER_ARCHIVE_STARTED = "User archive started. {}";
    private static final String USER_ARCHIVE_SUCCEED = "User archive succeed. {}";
    private static final String USER_RESTORE_STARTED = "User restore started. {}";
    private static final String USER_RESTORE_SUCCEED = "User restore succeed. {}";

    private final UserDao userDao;
    private final Logger logger;

    UserService(final UserDao userDao, final Logger logger) {
        this.userDao = userDao;
        this.logger = logger;
    }


    public User create(final UserRequest request) {
        logger.info(USER_CREATE_STARTED, request);
        User user = createUserWithRequestData(request);
        userDao.save(user);
        logger.info(USER_CREATE_SUCCEED, user);
        return user;
    }

    public User findById(final Long id) {
        logger.info(USER_SEARCH_STARTED, id);
        Optional<User> user = userDao.findById(id);
        if (user.isEmpty()) {
            logger.warn(USER_NOT_FOUND, id);
            throw new NoSuchElementException("User Not Found.");
        }
        logger.info(USER_SEARCH_SUCCEED, id);
        return user.get();
    }

    public List<User> findAll() {
        logger.info(USERS_ALL_SEARCH_STARTED);
        List<User> userList = userDao.findAll();
        logger.info(USERS_ALL_SEARCH_SUCCEED);
        return userList;
    }

    public void update(final UserRequest request, final User user) {
        logger.info(USER_UPDATE_STARTED, user);
        user.setNickname(request.getNickname());
        user.setGivenName(request.getGivenName());
        user.setFamilyName(request.getFamilyName());
        user.setEmail(request.getEmail());
        user.setDescription(request.getDescription());
        user.setImageUri(request.getImageUri());
        userDao.save(user);
        logger.info(USER_UPDATE_SUCCEED, user);
    }

    public void archive(final User user) {
        logger.info(USER_ARCHIVE_STARTED, user);
        user.setArchived(true);
        userDao.save(user);
        logger.info(USER_ARCHIVE_SUCCEED, user);
    }

    public void restore(final User user) {
        logger.info(USER_RESTORE_STARTED, user);
        user.setArchived(false);
        userDao.save(user);
        logger.info(USER_RESTORE_SUCCEED, user);
    }

    User createUserWithRequestData(final UserRequest request) {
        return User.builder()
            .withArchived(request.getArchived())
            .withDescription(request.getDescription())
            .withEmail(request.getEmail())
            .withFamilyName(request.getFamilyName())
            .withGivenName(request.getGivenName())
            .withImageUri(request.getImageUri())
            .withNickname(request.getNickname())
            .build();
    }
}

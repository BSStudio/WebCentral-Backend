package hu.bme.sch.bss.webcentral.service;

import hu.bme.sch.bss.webcentral.dao.UserDao;
import hu.bme.sch.bss.webcentral.domain.UserRequest;
import hu.bme.sch.bss.webcentral.model.User;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class UserService {


    private static final String USER_CREATE_STARTED = "User creation started. {}";
    private static final String USER_CREATE_SUCCEED = "User creation succeed. {}";


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

    private User createUserWithRequestData(UserRequest request) {
        return User.builder()
            .withDescription(request.getDescription())
            .withEmail(request.getEmail())
            .withFamilyName(request.getFamilyName())
            .withGivenName(request.getGivenName())
            .withImageURI(request.getImageURI())
            .withNickname(request.getNickname())
            .build();
    }
}

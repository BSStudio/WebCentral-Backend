package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.UserPostDao;
import hu.bme.sch.bss.webcentral.core.domain.UserPostRequest;
import hu.bme.sch.bss.webcentral.core.model.UserPost;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("designforextension")
public class UserPostService {

    private static final String USER_POST_CREATE_STARTED = "User creation started. {}";
    private static final String USER_POST_CREATE_SUCCEED = "User creation succeed. {}";


    private final UserPostDao userPostDao;
    private  final Logger logger;

    public UserPostService(final UserPostDao userPostDao, final Logger logger) {
        this.userPostDao = userPostDao;
        this.logger = logger;
    }

    public UserPost create(final UserPostRequest request) {
        logger.info(USER_POST_CREATE_STARTED, request);
        UserPost userPost = createUserPostWithRequestData(request);
        userPostDao.save(userPost);
        logger.info(USER_POST_CREATE_SUCCEED, request);
        return userPost;
    }

    private UserPost createUserPostWithRequestData(final UserPostRequest request) {
        return UserPost.builder()
            .withName(request.getName())
            .build();
    }
}

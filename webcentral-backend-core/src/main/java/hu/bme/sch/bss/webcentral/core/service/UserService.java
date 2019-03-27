package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.UserDao;
import hu.bme.sch.bss.webcentral.core.domain.UserRequest;
import hu.bme.sch.bss.webcentral.core.model.User;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public final class UserService {


	private static final String USER_CREATE_STARTED = "User creation started. {}";
	private static final String USER_CREATE_SUCCEED = "User creation succeed. {}";
	private static final String USER_SEARCH_STARTED = "User search started. {}";
	private static final String USER_SEARCH_SUCCEED = "User search succeed. {}";
	private static final String USER_NOT_FOUND = "User not found with id {}";
	private static final String USERS_ALL_SEARCH_STARTED = "Search for all users started";

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
		return userList;
	}

	private User createUserWithRequestData(final UserRequest request) {
		return User.builder()
				.withDescription(request.getDescription())
				.withEmail(request.getEmail())
				.withFamilyName(request.getFamilyName())
				.withGivenName(request.getGivenName())
				.withImageUri(request.getImageUri())
				.withNickname(request.getNickname())
				.build();
	}
}

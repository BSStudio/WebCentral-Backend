package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.StatusDao;
import hu.bme.sch.bss.webcentral.core.domain.StatusRequest;
import hu.bme.sch.bss.webcentral.core.model.Status;
import hu.bme.sch.bss.webcentral.core.model.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public final class StatusService {

    private static final String STATUS_CREATE_STARTED = "Status creation started. {}";
    private static final String STATUS_CREATE_SUCCEED = "Status creation succeed. {}";
    private static final String STATUS_SEARCH_STARTED = "Status search started {}";
    private static final String STATUS_SEARCH_SUCCEED = "Status search succeed {}";
    private static final String STATUS_SEARCH_WITH_NAME_SUCCEED = "Status search succeed {}";
    private static final String STATUS_NOT_FOUND = "Status not found with id: {}";
    private static final String STATUS_DELETE_STARTED = "Status delete started. {}";
    private static final String STATUS_DELETE_SUCCEED = "Status delete succeed. {}";
    private static final String STATUS_EDIT_STARTED = "Status edit started. {}";
    private static final String STATUS_EDIT_SUCCEED = "Status edit succeed. {}";
    private static final String STATUSES_ALL_SEARCH_STARTED = "Search for all statuses started.";
    private static final String STATUSES_ALL_SEARCH_SUCCEED = "Search for all statuses succeed.";
    private static final String STATUS_SEARCH_WITH_NAME_FAILURE = "Status not found with name {}";
    private static final String USER_SEARCH_BY_STATUS_ID_STARTED = "User search by status started with id: {}";
    private static final String USER_SEARCH_BY_STATUS_ID_SUCCEED = "User search by status succeed with id: {}";

    private final StatusDao statusDao;
    private final Logger logger;

    StatusService(final StatusDao statusDao, final Logger logger) {
        this.statusDao = statusDao;
        this.logger = logger;
    }

    public Status create(final StatusRequest request) {
        logger.info(STATUS_CREATE_STARTED, request);
        final Status status = Status.builder()
                .withName(request.getName())
                .build();
        final Status result = statusDao.save(status);
        logger.info(STATUS_CREATE_SUCCEED, request);
        return result;
    }

    public Status findById(final Long id) {
        logger.info(STATUS_SEARCH_STARTED, id);
        return statusDao.findById(id).map(status -> {
            logger.info(STATUS_SEARCH_SUCCEED, id);
            return status;
        }).orElseThrow(() -> {
            logger.warn(STATUS_NOT_FOUND, id);
            throw new NoSuchElementException("Status not found");
        });
    }

    public void delete(final Status status) {
        logger.info(STATUS_DELETE_STARTED, status);
        statusDao.delete(status);
        logger.info(STATUS_DELETE_SUCCEED, status);
    }

    public Status update(final StatusRequest request, final Status status) {
        logger.info(STATUS_EDIT_STARTED, request);
        status.setName(request.getName());
        final Status result = statusDao.save(status);
        logger.info(STATUS_EDIT_SUCCEED, request);
        return result;
    }

    public List<Status> findAll() {
        logger.info(STATUSES_ALL_SEARCH_STARTED);
        final List<Status> statusList = statusDao.findAll();
        logger.info(STATUSES_ALL_SEARCH_SUCCEED);
        return statusList;
    }

    public Status findByName(final String name) {
        return statusDao.findByName(name).map(status -> {
            logger.info(STATUS_SEARCH_WITH_NAME_SUCCEED, name);
            return status;
        }).orElseThrow(() -> {
            logger.warn(STATUS_SEARCH_WITH_NAME_FAILURE, name);
            return new NoSuchElementException("Status Type Not Found");
        });
    }

    public Set<User> findAllUserByStatusId(final Long id) {
        logger.info(USER_SEARCH_BY_STATUS_ID_STARTED, id);
        final Status status = findById(id);
        logger.info(USER_SEARCH_BY_STATUS_ID_SUCCEED, id);
        return status.getUsers();
    }
}

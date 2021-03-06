package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.PositionDao;
import hu.bme.sch.bss.webcentral.core.domain.PositionRequest;
import hu.bme.sch.bss.webcentral.core.model.Position;
import hu.bme.sch.bss.webcentral.core.model.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;


@Component
public final class PositionService {

    private static final String POSITION_CREATE_STARTED = "Position creation started. {}";
    private static final String POSITION_CREATE_SUCCEED = "Position creation succeed. {}";
    private static final String POSITION_SEARCH_STARTED = "Position search started {}";
    private static final String POSITION_SEARCH_SUCCEED = "Position search succeed {}";
    private static final String POSITION_NOT_FOUND = "Position not found with id: {}";
    private static final String POSITION_DELETE_STARTED = "Position delete started. {}";
    private static final String POSITION_DELETE_SUCCEED = "Position delete succeed. {}";
    private static final String POSITION_EDIT_STARTED = "Position edit started. {}";
    private static final String POSITION_EDIT_SUCCEED = "Position edit succeed. {}";
    private static final String POSITION_ALL_SEARCH_STARTED = "Search for all position started";
    private static final String POSITION_ALL_SEARCH_SUCCEED = "Search for all position succeed";
    private static final String POSITION_SEARCH_WITH_NAME_STARTED = "Position search started with name: {}";
    private static final String POSITION_SEARCH_WITH_NAME_FAILURE = "Position not found with name {}";
    private static final String USER_SEARCH_BY_POSITION_ID_STARTED = "User search by position started with id: {}";
    private static final String USER_SEARCH_BY_POSITION_ID_SUCCEED = "User search by position succeed with id: {}";

    private final PositionDao positionDao;
    private final Logger logger;

    PositionService(final PositionDao positionDao, final Logger logger) {
        this.positionDao = positionDao;
        this.logger = logger;
    }

    public Position create(final PositionRequest request) {
        logger.info(POSITION_CREATE_STARTED, request);
        final Position position = Position.builder()
                .withName(request.getName())
                .build();
        final Position result = positionDao.save(position);
        logger.info(POSITION_CREATE_SUCCEED, request);
        return result;
    }

    public Position findById(final Long id) {
        logger.info(POSITION_SEARCH_STARTED, id);
        return positionDao.findById(id).map(position -> {
            logger.info(POSITION_SEARCH_SUCCEED, id);
            return position;
        }).orElseThrow(() -> {
            logger.warn(POSITION_NOT_FOUND, id);
            return new NoSuchElementException("Position not found");
        });
    }

    public void delete(final Position position) {
        logger.info(POSITION_DELETE_STARTED, position);
        positionDao.delete(position);
        logger.info(POSITION_DELETE_SUCCEED, position);
    }

    public Position update(final PositionRequest request, final Position position) {
        logger.info(POSITION_EDIT_STARTED, request);
        position.setName(request.getName());
        final Position result = positionDao.save(position);
        logger.info(POSITION_EDIT_SUCCEED, request);
        return result;
    }

    public List<Position> findAll() {
        logger.info(POSITION_ALL_SEARCH_STARTED);
        final List<Position> positionList = positionDao.findAll();
        logger.info(POSITION_ALL_SEARCH_SUCCEED);
        return positionList;
    }

    public Position findByName(final String name) {
        return positionDao.findByName(name).map(position -> {
            logger.info(POSITION_SEARCH_WITH_NAME_STARTED, name);
            return position;
        }).orElseThrow(() -> {
            logger.warn(POSITION_SEARCH_WITH_NAME_FAILURE, name);
            return new NoSuchElementException("Position Type Not Found");
        });
    }

    public Set<User> findAllUserByPositionId(final Long id) {
        logger.info(USER_SEARCH_BY_POSITION_ID_STARTED, id);
        final Position position = findById(id);
        logger.info(USER_SEARCH_BY_POSITION_ID_SUCCEED, id);
        return position.getUsers();
    }
}

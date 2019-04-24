package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.PositionDao;
import hu.bme.sch.bss.webcentral.core.domain.PositionRequest;
import hu.bme.sch.bss.webcentral.core.model.Position;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@SuppressWarnings("designforextension")
public class PositionService {

    private static final String USER_POST_CREATE_STARTED = "User creation started. {}";
    private static final String USER_POST_CREATE_SUCCEED = "User creation succeed. {}";
    private static final String POSITION_SEARCH_STARTED = "User search started {}";
    private static final String POSITION_SEARCH_SUCCEED = "User search succeed {}";
    private static final String POSITION_NOT_FOUND = "User not found with id: {}";

    private final PositionDao positionDao;
    private  final Logger logger;

    public PositionService(final PositionDao positionDao, final Logger logger) {
        this.positionDao = positionDao;
        this.logger = logger;
    }

    public Position create(final PositionRequest request) {
        logger.info(USER_POST_CREATE_STARTED, request);
        Position position = createPositionWithRequestData(request);
        positionDao.save(position);
        logger.info(USER_POST_CREATE_SUCCEED, request);
        return position;
    }

    Position createPositionWithRequestData(final PositionRequest request) {
        return Position.builder()
            .withName(request.getName())
            .build();
    }

    public Position findById(final Long id) {
        logger.info(POSITION_SEARCH_STARTED, id);
        Optional<Position> result = positionDao.findById(id);
        if (result.isEmpty()) {
            logger.warn(POSITION_NOT_FOUND, id);
            throw new NoSuchElementException("Position not found");
        }
        logger.info(POSITION_SEARCH_SUCCEED, id);
        return result.get();
    }
}

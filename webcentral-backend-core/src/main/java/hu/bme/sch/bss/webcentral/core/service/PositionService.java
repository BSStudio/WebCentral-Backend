package hu.bme.sch.bss.webcentral.core.service;

import hu.bme.sch.bss.webcentral.core.dao.PositionDao;
import hu.bme.sch.bss.webcentral.core.domain.PositionRequest;
import hu.bme.sch.bss.webcentral.core.model.Position;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("designforextension")
public class PositionService {

    private static final String USER_POST_CREATE_STARTED = "User creation started. {}";
    private static final String USER_POST_CREATE_SUCCEED = "User creation succeed. {}";

    private final PositionDao positionDao;
    private  final Logger logger;

    public PositionService(final PositionDao positionDao, final Logger logger) {
        this.positionDao = positionDao;
        this.logger = logger;
    }

    public Position create(final PositionRequest request) {
        logger.info(USER_POST_CREATE_STARTED, request);
        Position position = createUserPostWithRequestData(request);
        positionDao.save(position);
        logger.info(USER_POST_CREATE_SUCCEED, request);
        return position;
    }

    private Position createUserPostWithRequestData(final PositionRequest request) {
        return Position.builder()
            .withName(request.getName())
            .build();
    }
}

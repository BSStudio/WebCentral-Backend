package hu.bme.sch.bss.webcentral.controller.core;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import hu.bme.sch.bss.webcentral.core.domain.PositionListResponse;
import hu.bme.sch.bss.webcentral.core.domain.PositionRequest;
import hu.bme.sch.bss.webcentral.core.domain.PositionResponse;
import hu.bme.sch.bss.webcentral.core.model.Position;
import hu.bme.sch.bss.webcentral.core.service.PositionService;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/position", produces = APPLICATION_JSON_VALUE)
public final class PositionController {

    private static final String REQUEST_POSITION_CREATE = "Request for position creation received. {}";
    private static final String REQUEST_POSITION_SEARCH = "Request for position search received with id of: {}";
    private static final String REQUEST_POSITION_DELETE = "Request to delete position received for id: {}";
    private static final String REQUEST_POSITION_EDIT = "Request to update user received for id {}";
    private static final String REQUEST_POSITION_LIST = "Request to find all positions received.";
    private final PositionService positionService;
    private final Logger logger;

    PositionController(final PositionService positionService, final Logger logger) {
        this.positionService = positionService;
        this.logger = logger;
    }

    @PostMapping()
    @ResponseStatus(CREATED)
    public PositionResponse createUser(@Valid @RequestBody final PositionRequest request) {
        logger.info(REQUEST_POSITION_CREATE, request);
        final Position result = positionService.create(request);
        return new PositionResponse(result);
    }

    @GetMapping("/{id}")
    @ResponseStatus(FOUND)
    public PositionResponse getPosition(@PathVariable("id") final Long id) {
        logger.info(REQUEST_POSITION_SEARCH, id);
        final Position result = positionService.findById(id);
        return new PositionResponse(result);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void deletePosition(@PathVariable("id") final Long id) {
        logger.info(REQUEST_POSITION_DELETE, id);
        final Position position = positionService.findById(id);
        positionService.delete(position);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public PositionResponse updatePosition(@PathVariable("id") final Long id, @Valid @RequestBody final PositionRequest request) {
        logger.info(REQUEST_POSITION_EDIT, id);
        final Position position = positionService.findById(id);
        final Position result = positionService.update(request, position);
        return new PositionResponse(result);
    }

    @GetMapping("/all")
    @ResponseStatus(FOUND)
    public PositionListResponse listAllPositions() {
        logger.info(REQUEST_POSITION_LIST);
        final List<Position> positions = positionService.findAll();
        return PositionListResponse.builder()
            .withPositions(positions)
            .build();
    }
}

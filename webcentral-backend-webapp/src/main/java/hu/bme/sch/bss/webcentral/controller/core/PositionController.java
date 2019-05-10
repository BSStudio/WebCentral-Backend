package hu.bme.sch.bss.webcentral.controller.core;

import hu.bme.sch.bss.webcentral.core.domain.PositionListResponse;
import hu.bme.sch.bss.webcentral.core.domain.PositionRequest;
import hu.bme.sch.bss.webcentral.core.domain.PositionResponse;
import hu.bme.sch.bss.webcentral.core.model.Position;
import hu.bme.sch.bss.webcentral.core.service.PositionService;

import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;

import org.springframework.http.HttpStatus;
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
@RequestMapping(value = "/api/user/position", produces = "application/json")
public class PositionController {


    private static final String REQUEST_POSITION_CREATE = "Request for position creation received. {}";
    private static final String REQUEST_POSITION_SEARCH = "Request for position search received with id of: {}";
    private static final String REQUEST_POSITION_DELETE = "Request to delete position received for id: {}";
    private static final String REQUEST_POSITION_EDIT = "Request to update user received for id {}";
    private static final String REQUEST_POSITION_LIST = "Request to find all positions received.";
    private final PositionService positionService;
    private final Logger logger;

    public PositionController(final PositionService userService, final Logger logger) {
        this.positionService = userService;
        this.logger = logger;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public final PositionResponse createUser(@Valid @RequestBody final PositionRequest request) {
        logger.info(REQUEST_POSITION_CREATE, request);
        Position result = positionService.create(request);
        return new PositionResponse(result);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public final PositionResponse getPosition(@PathVariable("id") final Long id) {
        logger.info(REQUEST_POSITION_SEARCH, id);
        Position result = positionService.findById(id);
        return new PositionResponse(result);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public final void deletePosition(@PathVariable("id") final Long id) {
        logger.info(REQUEST_POSITION_DELETE, id);
        Position position = positionService.findById(id);
        positionService.delete(position);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public final PositionResponse updatePosition(@PathVariable("id") final Long id, @Valid @RequestBody final PositionRequest request) {
        logger.info(REQUEST_POSITION_EDIT, id);
        Position position = positionService.findById(id);
        positionService.update(request, position);
        return new PositionResponse(position);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.FOUND)
    public final PositionListResponse listAllPositions() {
        logger.info(REQUEST_POSITION_LIST);
        ArrayList<Position> positions = new ArrayList<>(positionService.findAll());
        return PositionListResponse.builder()
            .withPositions(positions)
            .build();
    }
}

package hu.bme.sch.bss.webcentral.controller.core;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.OK;

import hu.bme.sch.bss.webcentral.core.domain.StatusListResponse;
import hu.bme.sch.bss.webcentral.core.domain.StatusRequest;
import hu.bme.sch.bss.webcentral.core.domain.StatusResponse;
import hu.bme.sch.bss.webcentral.core.model.Status;
import hu.bme.sch.bss.webcentral.core.service.StatusService;

import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;

import org.springframework.http.MediaType;
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
@RequestMapping(value = "/api/status", produces = MediaType.APPLICATION_JSON_VALUE)
public final class StatusController {

    private static final String REQUEST_STATUS_CREATE = "Request for status creation received. {}";
    private static final String REQUEST_STATUS_SEARCH = "Request for status search received with id of: {}";
    private static final String REQUEST_STATUS_DELETE = "Request to delete status received for id: {}";
    private static final String REQUEST_STATUS_EDIT = "Request to update user received for id {}";
    private static final String REQUEST_STATUS_LIST = "Request to find all statuses received.";

    private final StatusService statusService;
    private final Logger logger;

    StatusController(final StatusService userService, final Logger logger) {
        this.statusService = userService;
        this.logger = logger;
    }

    @PostMapping()
    @ResponseStatus(CREATED)
    public StatusResponse createStatus(@Valid @RequestBody final StatusRequest request) {
        logger.info(REQUEST_STATUS_CREATE, request);
        final Status result = statusService.create(request);
        return new StatusResponse(result);
    }

    @GetMapping("/{id}")
    @ResponseStatus(FOUND)
    public StatusResponse getStatus(@PathVariable("id") final Long id) {
        logger.info(REQUEST_STATUS_SEARCH, id);
        final Status result = statusService.findById(id);
        return new StatusResponse(result);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void deleteStatus(@PathVariable("id") final Long id) {
        logger.info(REQUEST_STATUS_DELETE, id);
        final Status status = statusService.findById(id);
        statusService.delete(status);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public StatusResponse updateStatus(@PathVariable("id") final Long id, @Valid @RequestBody final StatusRequest request) {
        logger.info(REQUEST_STATUS_EDIT, id);
        final Status status = statusService.findById(id);
        final Status result = statusService.update(request, status);
        return new StatusResponse(result);
    }

    @GetMapping("/all")
    @ResponseStatus(FOUND)
    public StatusListResponse listAllStatuses() {
        logger.info(REQUEST_STATUS_LIST);
        final ArrayList<Status> statuses = new ArrayList<>(statusService.findAll());
        return StatusListResponse.builder()
            .withStatuses(statuses)
            .build();
    }
}

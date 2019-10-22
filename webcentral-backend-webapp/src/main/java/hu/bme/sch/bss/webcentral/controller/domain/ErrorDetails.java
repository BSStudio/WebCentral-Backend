package hu.bme.sch.bss.webcentral.controller.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

import org.springframework.http.HttpStatus;

@JsonSerialize
public final class ErrorDetails {

    private final Date timestamp;
    private final HttpStatus status;
    private final String message;
    private final String details;
    private final String path;

    public ErrorDetails(final Date timestamp, final HttpStatus status, final String message, final String details, final String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.details = details;
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status.value();
    }

    public String getError() {
        return status.getReasonPhrase();
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public String getPath() {
        return path;
    }
}

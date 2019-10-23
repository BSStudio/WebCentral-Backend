package hu.bme.sch.bss.webcentral.core.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hu.bme.sch.bss.webcentral.core.model.Status;

import java.util.List;

import lombok.Getter;

@JsonSerialize
@Getter
public final class StatusListResponse {

    private Status[] statuses;

    StatusListResponse(final Status[] statuses) {
        this.statuses = statuses;
    }

    public StatusListResponse(final List<Status> statuses) {
        this.statuses = new Status[statuses.size()];
        this.statuses = statuses.toArray(this.statuses);
    }

}

package hu.bme.sch.bss.webcentral.core.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hu.bme.sch.bss.webcentral.core.model.Status;

import lombok.Getter;

@JsonSerialize
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName("status")
@Getter
public final class StatusResponse {

    private final Long id;

    private final String name;

    public StatusResponse(final Status result) {
        this.id = result.getId();
        this.name = result.getName();
    }
}

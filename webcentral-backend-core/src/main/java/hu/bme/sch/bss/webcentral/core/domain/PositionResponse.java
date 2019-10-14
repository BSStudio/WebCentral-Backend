package hu.bme.sch.bss.webcentral.core.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hu.bme.sch.bss.webcentral.core.model.Position;

@JsonSerialize
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName("position")
public final class PositionResponse {

    private final Long id;

    private final String name;

    public PositionResponse(final Position result) {
        this.id = result.getId();
        this.name = result.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

package hu.bme.sch.bss.webcentral.core.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hu.bme.sch.bss.webcentral.core.model.Position;

import java.util.List;

import lombok.Getter;

@Getter
@JsonSerialize
public final class PositionListResponse {

    private Position[] positions;

    PositionListResponse(final Position[] positions) {
        this.positions = positions;
    }

    public PositionListResponse(final List<Position> positions) {
        this.positions = new Position[positions.size()];
        this.positions = positions.toArray(this.positions);
    }

}

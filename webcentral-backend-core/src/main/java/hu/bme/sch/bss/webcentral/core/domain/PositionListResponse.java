package hu.bme.sch.bss.webcentral.core.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.core.model.Position;

import java.util.List;

@JsonSerialize
public final class PositionListResponse {

    private final Position[] position;

    public Position[] getPositions() {
        return position;
    }

    public PositionListResponse(final Builder builder) {
        this.position = builder.position;
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("hiddenfield")
    public static final class Builder {
        private Position[] position;

        public Builder withPositions(final Position[] position) {
            this.position = position;
            return this;
        }

        public Builder withPositions(final List<Position> position) {
            this.position = new Position[position.size()];
            this.position = position.toArray(this.position);
            return this;
        }

        public PositionListResponse build() {
            return new PositionListResponse(this);
        }
    }
}

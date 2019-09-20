package hu.bme.sch.bss.webcentral.core.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotBlank;

@SuppressWarnings("finalclass")
@JsonDeserialize(builder = PositionRequest.Builder.class)
@JsonRootName("position")
public class PositionRequest {

    @NotBlank
    private String name;

    private PositionRequest(final Builder builder) {
        this.name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    @SuppressWarnings("hiddenfield")
    public static final class Builder {

        private String name;

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public PositionRequest build() {
            return new PositionRequest(this);
        }
    }
}

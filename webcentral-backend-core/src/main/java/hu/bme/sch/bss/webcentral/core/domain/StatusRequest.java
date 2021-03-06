package hu.bme.sch.bss.webcentral.core.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@JsonDeserialize(builder = StatusRequest.Builder.class)
@JsonRootName("status")
@Getter
public final class StatusRequest {

    @NotBlank
    private String name;

    private StatusRequest(final Builder builder) {
        this.name = builder.name;
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("hiddenfield")
    public static final class Builder {

        private String name;

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public StatusRequest build() {
            return new StatusRequest(this);
        }
    }
}

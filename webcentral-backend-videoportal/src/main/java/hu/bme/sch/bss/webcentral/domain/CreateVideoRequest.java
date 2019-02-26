package hu.bme.sch.bss.webcentral.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonDeserialize(builder = CreateVideoRequest.Builder.class)
public final class CreateVideoRequest {

    @NotBlank
    private final String longName;

    @NotBlank
    private final String canonicalName;

    @NotBlank
    private final String description;

    @NotNull
    private final Boolean visible;

    private CreateVideoRequest(final Builder builder) {
        this.longName = builder.longName;
        this.canonicalName = builder.canonicalName;
        this.description = builder.description;
        this.visible = builder.visible;
    }

    public String getLongName() {
        return longName;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getVisible() {
        return visible;
    }

    @SuppressWarnings("hiddenfield")
    public static final class Builder {

        private @NotBlank String longName;
        private @NotBlank String canonicalName;
        private @NotBlank String description;
        private @NotNull Boolean visible;

        public Builder withLongName(@NotBlank final String longName) {
            this.longName = longName;
            return this;
        }

        public Builder withCanonicalName(@NotBlank final String canonicalName) {
            this.canonicalName = canonicalName;
            return this;
        }

        public Builder withDescription(@NotBlank final String description) {
            this.description = description;
            return this;
        }

        public Builder withVisible(@NotNull final Boolean visible) {
            this.visible = visible;
            return this;
        }

        public CreateVideoRequest build() {
            return new CreateVideoRequest(this);
        }
    }
}

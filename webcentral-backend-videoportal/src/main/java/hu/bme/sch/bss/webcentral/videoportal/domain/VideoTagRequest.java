package hu.bme.sch.bss.webcentral.videoportal.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotBlank;

@JsonDeserialize(builder = VideoTagRequest.Builder.class)
@JsonRootName("videoTag")
public class VideoTagRequest {
    @NotBlank
    private final String longName;

    @NotBlank
    private final String canonicalName;

    private final String description;

    private VideoTagRequest(final Builder builder) {
        this.longName = builder.longName;
        this.canonicalName = builder.canonicalName;
        this.description = builder.description;
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

    @Override
    public String toString() {
        return "VideoTagRequest{" +
                "longName='" + longName + '\'' +
                ", canonicalName='" + canonicalName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static final class Builder{
        private String longName;
        private String canonicalName;
        private String description;

        public Builder withLongName(final String longName) {
            this.longName = longName;
            return this;
        }

        public Builder withCanonicalName(final String canonicalName) {
            this.canonicalName = canonicalName;
            return this;
        }

        public Builder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public VideoTagRequest build() {
            return new VideoTagRequest(this);
        }
    }
}

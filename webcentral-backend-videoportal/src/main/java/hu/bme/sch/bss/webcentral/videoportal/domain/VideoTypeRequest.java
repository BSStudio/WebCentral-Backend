package hu.bme.sch.bss.webcentral.videoportal.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@SuppressWarnings("finalclass")
@JsonDeserialize(builder = VideoTypeRequest.Builder.class)
@JsonRootName("videoType")
public class VideoTypeRequest {

    @NotBlank
    private final String longName;
    @NotBlank
    private final String canonicalName;
    @NotBlank
    private final String description;

    private VideoTypeRequest(Builder builder) {
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

    // Generated code begins here

    @Override
    public String toString() {
        return "VideoTypeRequest{"
            + "longName='" + longName + '\''
            + ", canonicalName='" + canonicalName + '\''
            + ", description='" + description + '\''
            + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VideoTypeRequest that = (VideoTypeRequest) o;
        return Objects.equals(longName, that.longName)
            && Objects.equals(canonicalName, that.canonicalName)
            && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(longName, canonicalName, description);
    }

    // Generated code ends here

    public static final class Builder {

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

        public VideoTypeRequest build() {
            return new VideoTypeRequest(this);
        }
    }
}

package hu.bme.sch.bss.webcentral.videoportal.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SuppressWarnings("finalclass")
@JsonDeserialize(builder = VideoRequest.Builder.class)
@JsonRootName("video")
public class VideoRequest {

    @NotBlank
    private final String longName;

    @NotBlank
    private final String canonicalName;

    @NotBlank
    private final String projectName;

    @NotBlank
    private final String description;

    @NotNull
    private final Boolean visible;

    @NotBlank
    private final String videoLocation;

    @NotBlank
    private final String imageLocation;

    private VideoRequest(final Builder builder) {
        this.longName = builder.longName;
        this.canonicalName = builder.canonicalName;
        this.projectName = builder.projectName;
        this.description = builder.description;
        this.visible = builder.visible;
        this.videoLocation = builder.videoLocation;
        this.imageLocation = builder.imageLocation;
    }

    public String getLongName() {
        return longName;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getVisible() {
        return visible;
    }

    public String getVideoLocation() {
        return videoLocation;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "VideoRequest{"
            + "longName='" + longName + "'"
            + ", canonicalName='" + canonicalName + "'"
            + ", projectName='" + projectName + "'"
            + ", description='" + description + "'"
            + ", visible=" + visible
            + ", videoLocation='" + videoLocation + "'"
            + ", imageLocation='" + imageLocation + "'"
            + '}';
    }

    @SuppressWarnings("hiddenfield")
    public static final class Builder {

        private String longName;
        private String canonicalName;
        private String projectName;
        private String description;
        private Boolean visible;
        private String videoLocation;
        private String imageLocation;


        public Builder withLongName(final String longName) {
            this.longName = longName;
            return this;
        }

        public Builder withCanonicalName(final String canonicalName) {
            this.canonicalName = canonicalName;
            return this;
        }

        public Builder withProjectName(final String projectName) {
            this.projectName = projectName;
            return this;
        }

        public Builder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public Builder withVisible(final Boolean visible) {
            this.visible = visible;
            return this;
        }

        public Builder withVideoLocation(final String videoLocation) {
            this.videoLocation = videoLocation;
            return this;
        }

        public Builder withImageLocation(final String imageLocation) {
            this.imageLocation = imageLocation;
            return this;
        }

        public VideoRequest build() {
            return new VideoRequest(this);
        }
    }
}

package hu.bme.sch.bss.webcentral.videoportal.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hu.bme.sch.bss.webcentral.DomainAuditModel;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonSerialize
@JsonDeserialize(builder = Video.Builder.class)
@Entity
@Table(name = "videos")
public final class Video extends DomainAuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String longName;

    @NotBlank
    private String canonicalName;

    @NotBlank
    private String projectName;

    @NotBlank
    private String description;

    @NotNull
    private Boolean visible;

    @NotNull
    private Boolean archived;

    @NotBlank
    private String videoLocation;

    @NotBlank
    private String imageLocation;

    // No-arg constructor for hibernate
    public Video() {
    }

    private Video(final Builder builder) {
        this.longName = builder.longName;
        this.canonicalName = builder.canonicalName;
        this.projectName = builder.projectName;
        this.description = builder.description;
        this.visible = builder.visible;
        this.videoLocation = builder.videoLocation;
        this.imageLocation = builder.imageLocation;
        this.archived = false;
    }

    public Long getId() {
        return id;
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

    public Boolean getArchived() {
        return archived;
    }

    public String getVideoLocation() {
        return videoLocation;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setArchived(@NotNull final Boolean archived) {
        this.archived = archived;
    }

    public static Builder builder() {
        return new Builder();
    }

    // Generated code begins here


    @Override
    public String toString() {
        return "Video{"
                + "id=" + id
                + ", longName='" + longName
                + ", canonicalName='" + canonicalName
                + ", projectName='" + projectName
                + ", description='" + description
                + ", visible=" + visible
                + ", archived=" + archived
                + ", videoLocation='" + videoLocation
                + ", imageLocation='" + imageLocation
                + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Video video = (Video) o;
        return Objects.equals(id, video.id)
                && Objects.equals(longName, video.longName)
                && Objects.equals(canonicalName, video.canonicalName)
                && Objects.equals(projectName, video.projectName)
                && Objects.equals(description, video.description)
                && Objects.equals(visible, video.visible)
                && Objects.equals(videoLocation, video.videoLocation)
                && Objects.equals(imageLocation, video.imageLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                longName,
                canonicalName,
                projectName,
                description,
                visible,
                videoLocation,
                imageLocation
        );
    }

    // Generated code ends here

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

        public Video build() {
            return new Video(this);
        }
    }
}

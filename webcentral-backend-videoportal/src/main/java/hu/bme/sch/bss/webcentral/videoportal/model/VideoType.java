package hu.bme.sch.bss.webcentral.videoportal.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.DomainAuditModel;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SuppressWarnings({"finalclass", "designforextension"})
@JsonSerialize
@JsonDeserialize(builder = VideoType.Builder.class)
@Entity
@Table(name = "video_types")
public class VideoType extends DomainAuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "long_name", nullable = false)
    private String longName;

    @NotBlank
    @Column(name = "canonical_name", nullable = false)
    private String canonicalName;

    @NotBlank
    private String description;

    @NotNull
    private Boolean archived;

    public VideoType() {
        // No-arg constructor for hibernate
    }

    private VideoType(final Builder builder) {
        this.longName = builder.longName;
        this.canonicalName = builder.canonicalName;
        this.description = builder.description;
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

    public String getDescription() {
        return description;
    }

    public Boolean getArchived() {
        return archived;
    }

    public static Builder builder() {
        return new Builder();
    }

    // Generated code begins here

    @Override
    public String toString() {
        return "VideoType{"
            + "id=" + id
            + ", longName='" + longName + '\''
            + ", canonicalName='" + canonicalName + '\''
            + ", description='" + description + '\''
            + ", archived=" + archived
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
        VideoType videoType = (VideoType) o;
        return Objects.equals(id, videoType.id)
            && Objects.equals(longName, videoType.longName)
            && Objects.equals(canonicalName, videoType.canonicalName)
            && Objects.equals(description, videoType.description)
            && Objects.equals(archived, videoType.archived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longName, canonicalName, description, archived);
    }

    // Generated code ends here

    @SuppressWarnings("hiddenfield")
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

        public VideoType build() {
            return new VideoType(this);
        }
    }

}

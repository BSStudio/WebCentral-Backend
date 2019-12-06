package hu.bme.sch.bss.webcentral.videoportal.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.core.DomainAuditModel;

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
@JsonDeserialize(builder = VideoTag.Builder.class)
@Entity
@Table(name = "video_tags")
public class VideoTag extends DomainAuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "long_name", nullable = false)
    private String longName;

    @NotBlank
    @Column(name = "canonical_name", nullable = false)
    private String canonicalName;

    private String description;

    private Boolean archived;

    public VideoTag() {
        // No-arg constructor for hibernate
    }

    public VideoTag(final Builder builder) {
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

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public void setCanonicalName(String canonicalName) {
        this.canonicalName = canonicalName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void archive() {
        this.archived = true;
    }

    public static Builder builder() {
        return new Builder();
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

        public VideoTag build() {
            return new VideoTag(this);
        }
    }

}

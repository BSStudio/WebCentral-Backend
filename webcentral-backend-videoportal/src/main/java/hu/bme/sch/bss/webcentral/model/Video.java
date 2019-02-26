package hu.bme.sch.bss.webcentral.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hu.bme.sch.bss.webcentral.DomainAuditModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonSerialize
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
    private String description;

    @NotNull
    private Boolean visible;

    private Video(final Builder builder) {
        this.longName = builder.longName;
        this.canonicalName = builder.canonicalName;
        this.description = builder.description;
        this.visible = builder.visible;
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

    public Boolean getVisible() {
        return visible;
    }

    public static Builder builder() {
        return new Builder();
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

        public Video build() {
            return new Video(this);
        }
    }
}

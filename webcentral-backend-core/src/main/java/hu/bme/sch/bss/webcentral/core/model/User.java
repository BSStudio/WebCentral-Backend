package hu.bme.sch.bss.webcentral.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hu.bme.sch.bss.webcentral.core.DomainAuditModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@JsonSerialize
@JsonDeserialize(builder = User.Builder.class)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data public final class User extends DomainAuditModel {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Boolean archived;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nickname;

    @NotBlank
    @Column(name = "given_name", nullable = false)
    private String givenName;

    @NotBlank
    @Column(name = "family_name", nullable = false)
    private String familyName;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @Column(name = "image_uri")
    private String imageUri;

    @NotNull
    @JoinColumn
    @ManyToOne
    @JsonIgnore
    private Status status;

    @NotNull
    @JoinColumn
    @ManyToOne
    @JsonIgnore
    private Position position;

    private User(final Builder builder) {
        this.archived = builder.archived;
        this.nickname = builder.nickname;
        this.givenName = builder.givenName;
        this.familyName = builder.familyName;
        this.email = builder.email;
        this.description = builder.description;
        this.imageUri = builder.imageUri;
        this.status = builder.status;
        this.position = builder.position;
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("hiddenfield")
    public static final class Builder {

        private Boolean archived = false;
        private String nickname;
        private String givenName;
        private String familyName;
        private String email;
        private String description;
        private String imageUri = "";
        private Status status;
        private Position position;

        public Builder withArchived(final Boolean archived) {
            this.archived = archived;
            return this;
        }

        public Builder withNickname(final String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder withGivenName(final String givenName) {
            this.givenName = givenName;
            return this;
        }

        public Builder withFamilyName(final String familyName) {
            this.familyName = familyName;
            return this;
        }

        public Builder withEmail(final String email) {
            this.email = email;
            return this;
        }

        public Builder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public Builder withImageUri(final String imageUri) {
            this.imageUri = imageUri;
            return this;
        }

        public Builder withStatus(final Status status) {
            this.status = status;
            return this;
        }

        public Builder withPosition(final Position position) {
            this.position = position;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

}

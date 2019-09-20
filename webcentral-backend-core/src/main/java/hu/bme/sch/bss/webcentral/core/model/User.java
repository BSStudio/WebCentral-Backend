package hu.bme.sch.bss.webcentral.core.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.core.DomainAuditModel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@JsonSerialize
@JsonDeserialize(builder = User.Builder.class)
@EqualsAndHashCode(callSuper = true) @NoArgsConstructor
@Data public final class User extends DomainAuditModel {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Boolean archived;

    @NotBlank
    private String nickname;

    @NotBlank
    private String givenName;

    @NotBlank
    private String familyName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String description;

    private String imageUri;

    public User(final Builder builder) {
        this.archived = builder.archived;
        this.nickname = builder.nickname;
        this.givenName = builder.givenName;
        this.familyName = builder.familyName;
        this.email = builder.email;
        this.description = builder.description;
        this.imageUri = builder.imageUri;
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("hiddenfield")
    public static final class Builder {

        private Boolean archived;
        private String nickname;
        private String givenName;
        private String familyName;
        private String email;
        private String description;
        private String imageUri;

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

        public User build() {
            return new User(this);
        }
    }
}

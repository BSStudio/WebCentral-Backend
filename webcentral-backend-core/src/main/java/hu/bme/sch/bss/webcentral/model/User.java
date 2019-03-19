package hu.bme.sch.bss.webcentral.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.DomainAuditModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@JsonSerialize
@JsonDeserialize(builder = User.Builder.class)
@Entity
@Table(name = "users")
public final class User extends DomainAuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nickname;

    @NotBlank
    private String givenName;

    @NotBlank
    private String familyName;

    @Email
    private String email;

    @NotBlank
    private String description;

    private String imageURI;

    public User() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURI() {
        return imageURI;
    }

    public User(final Builder builder) {
        this.nickname = builder.nickname;
        this.givenName = builder.givenName;
        this.familyName = builder.familyName;
        this.email = builder.email;
        this.description = builder.description;
        this.imageURI = builder.imageURI;
    }

    @Override
    public String toString() {
        return "User{"
            + "id=" + id
            + ", nickname='" + nickname + '\''
            + ", givenName='" + givenName + '\''
            + ", familyName='" + familyName + '\''
            + ", email='" + email + '\''
            + ", description='" + description + '\''
            + ", imageURI='" + imageURI + '\''
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
        User user = (User) o;
        return Objects.equals(id, user.id) &&
            Objects.equals(nickname, user.nickname) &&
            Objects.equals(givenName, user.givenName) &&
            Objects.equals(familyName, user.familyName) &&
            Objects.equals(email, user.email) &&
            Objects.equals(description, user.description) &&
            Objects.equals(imageURI, user.imageURI);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, givenName, familyName, email, description, imageURI);
    }

    public static final class Builder {

        private @NotBlank String nickname;
        private @NotBlank String givenName;
        private @NotBlank String familyName;
        private @Email String email;
        private @NotBlank String description;
        private String imageURI;

        public Builder withNickname(@NotBlank String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder withGivenName(@NotBlank String givenName) {
            this.givenName = givenName;
            return this;
        }

        public Builder withFamilyName(@NotBlank String familyName) {
            this.familyName = familyName;
            return this;
        }

        public Builder withEmail(@Email String email) {
            this.email = email;
            return this;
        }

        public Builder withDescription(@NotBlank String description) {
            this.description = description;
            return this;
        }

        public Builder withImageURI(String imageURI) {
            this.imageURI = imageURI;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

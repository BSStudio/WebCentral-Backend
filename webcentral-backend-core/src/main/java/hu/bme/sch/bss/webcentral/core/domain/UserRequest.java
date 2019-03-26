package hu.bme.sch.bss.webcentral.core.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@JsonDeserialize(builder = UserRequest.Builder.class)
@JsonRootName("user")
public class UserRequest {

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

    public UserRequest(final Builder builder) {
        this.nickname = builder.nickname;
        this.givenName = builder.givenName;
        this.familyName = builder.familyName;
        this.email = builder.email;
        this.description = builder.description;
        this.imageURI = builder.imageURI;
    }

    public static Builder builder() {
        return new Builder();
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

        public UserRequest build() {
            return new UserRequest(this);
        }
    }
}

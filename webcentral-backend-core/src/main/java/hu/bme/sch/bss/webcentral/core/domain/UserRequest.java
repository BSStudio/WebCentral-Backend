package hu.bme.sch.bss.webcentral.core.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SuppressWarnings("finalclass")
@JsonDeserialize(builder = UserRequest.Builder.class)
@JsonRootName("user")
public class UserRequest {

	@NotNull
	private Boolean archived;

    @NotBlank
    private String nickname;

    @NotBlank
    private final String givenName;

    @NotBlank
    private final String familyName;

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String description;

    @NotBlank
    private final String imageUri;

    private UserRequest(final Builder builder) {
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

	public Boolean getArchived() {
		return archived;
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

    public String getImageUri() {
        return imageUri;
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

        public UserRequest build() {
            return new UserRequest(this);
        }
    }
}

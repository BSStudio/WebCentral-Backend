package hu.bme.sch.bss.webcentral.core.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.core.DomainAuditModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@JsonSerialize
@JsonDeserialize(builder = User.Builder.class)
@Entity
@Table(name = "users")
public final class User extends DomainAuditModel {

	@Id
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

	public User() {
		// No-arg constructor for hibernate
	}

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

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public Long getId() {
		return id;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
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

	//Generated code begins here

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(id, user.id) &&
				Objects.equals(archived, user.archived) &&
				Objects.equals(nickname, user.nickname) &&
				Objects.equals(givenName, user.givenName) &&
				Objects.equals(familyName, user.familyName) &&
				Objects.equals(email, user.email) &&
				Objects.equals(description, user.description) &&
				Objects.equals(imageUri, user.imageUri);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, archived, nickname, givenName, familyName, email, description, imageUri);
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", archived=" + archived +
				", nickname='" + nickname + '\'' +
				", givenName='" + givenName + '\'' +
				", familyName='" + familyName + '\'' +
				", email='" + email + '\'' +
				", description='" + description + '\'' +
				", imageUri='" + imageUri + '\'' +
				'}';
	}

	// Generated code ends here

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

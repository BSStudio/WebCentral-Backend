package hu.bme.sch.bss.webcentral.core.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.core.model.User;

import java.util.List;

@JsonSerialize
public class UserListResponse {

	private final User[] users;

	public User[] getUsers() {
		return users;
	}

	public UserListResponse(final Builder builder) {
		this.users = builder.users;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private User[] users;

		public Builder withUsers(final User[] users) {
			this.users = users;
			return this;
		}

		public Builder withUsers(final List<User> users) {
			this.users = new User[users.size()];
			this.users = users.toArray(this.users);
			return this;
		}

		public UserListResponse build() {
			return new UserListResponse(this);
		}
	}
}

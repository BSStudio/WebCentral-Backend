package hu.bme.sch.bss.webcentral.core.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hu.bme.sch.bss.webcentral.core.model.User;

import java.util.List;
import java.util.Set;

import lombok.Getter;

@Getter
@JsonSerialize
public final class UserListResponse {

    private final User[] users;

    private UserListResponse(final Builder builder) {
        this.users = builder.users;
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("hiddenfield")
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

        public Builder withUsers(final Set<User> users) {
            this.users = new User[users.size()];
            this.users = users.toArray(this.users);
            return this;
        }

        public UserListResponse build() {
            return new UserListResponse(this);
        }

    }

}

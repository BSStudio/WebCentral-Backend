package hu.bme.sch.bss.webcentral.core.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hu.bme.sch.bss.webcentral.core.model.User;

import java.util.List;
import java.util.Set;

import lombok.Getter;

@Getter
@JsonSerialize
public final class UserListResponse {

    private User[] users;

    UserListResponse(final User[] users) {
        this.users = users;
    }

    public UserListResponse(final List<User> users) {
        this.users = new User[users.size()];
        this.users = users.toArray(this.users);
    }

    public UserListResponse(final Set<User> users) {
        this.users = new User[users.size()];
        this.users = users.toArray(this.users);
    }

}

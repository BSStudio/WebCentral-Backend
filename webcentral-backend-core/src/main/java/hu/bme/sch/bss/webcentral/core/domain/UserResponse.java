package hu.bme.sch.bss.webcentral.core.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import hu.bme.sch.bss.webcentral.core.model.Position;
import hu.bme.sch.bss.webcentral.core.model.Status;
import hu.bme.sch.bss.webcentral.core.model.User;

import lombok.Getter;

@JsonSerialize
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName("user")
@Getter
public final class UserResponse {

    private final Long id;
    private final Boolean archived;
    private final String nickname;
    private final String givenName;
    private final String familyName;
    private final String email;
    private final String description;
    private final String imageUri;
    private final Status status;
    private final Position position;


    public UserResponse(final User user) {
        this.id = user.getId();
        this.archived = user.getArchived();
        this.nickname = user.getNickname();
        this.givenName = user.getGivenName();
        this.familyName = user.getFamilyName();
        this.email = user.getEmail();
        this.description = user.getDescription();
        this.imageUri = user.getImageUri();
        this.status = user.getStatus();
        this.position = user.getPosition();
    }

}

package hu.bme.sch.bss.webcentral.core.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.core.model.User;

@JsonSerialize
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName("user")
public final class UserResponse {
    private final Long id;
    private final Boolean archived;
    private final String nickname;
    private final String givenName;
    private final String familyName;
    private final String email;
    private final String description;
    private final String imageUri;
    private final String status;
    private final String position;


    public UserResponse(final User user) {
        this.id = user.getId();
        this.archived = user.getArchived();
        this.nickname = user.getNickname();
        this.givenName = user.getGivenName();
        this.familyName = user.getFamilyName();
        this.email = user.getEmail();
        this.description = user.getDescription();
        this.imageUri = user.getImageUri();
        this.status = user.getStatus().getName();
        this.position = user.getPosition().getName();
    }

    public Long getId() {
        return id;
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

    public String getStatus() {
        return status;
    }

    public String getPosition() {
        return position;
    }
}

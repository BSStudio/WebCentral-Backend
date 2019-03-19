package hu.bme.sch.bss.webcentral.core.domain;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.core.model.User;

@JsonSerialize
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName("video")
public class UserResponse {
    private final Long id;
    private final String nickname;
    private final String givenName;
    private final String familyName;
    private final String email;
    private final String description;
    private final String imageURI;

    public UserResponse(final User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.givenName = user.getGivenName();
        this.familyName = user.getFamilyName();
        this.email = user.getEmail();
        this.description = user.getDescription();
        this.imageURI = user.getImageURI();
    }
}

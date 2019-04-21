package hu.bme.sch.bss.webcentral.core.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.NotNull;

@SuppressWarnings("finalclass")
@JsonDeserialize(builder = UserRequest.Builder.class)
@JsonRootName("user-post")
public final class UserPostRequest {

    @NotNull
    private String name;

    public UserPostRequest(final Builder builder) {
        this.name = builder.name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @SuppressWarnings("hiddenfield")
    public static final class Builder {

        private String name;

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public UserPostRequest build() {
            return new UserPostRequest(this);
        }
    }
}

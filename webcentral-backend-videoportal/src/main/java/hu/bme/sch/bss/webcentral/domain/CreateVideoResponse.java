package hu.bme.sch.bss.webcentral.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.model.Video;

@JsonSerialize
public final class CreateVideoResponse {

    private final Video video;

    public CreateVideoResponse(final Builder builder) {
        this.video = builder.video;
    }

    public Video getVideo() {
        return video;
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("hiddenfield")
    public static final class Builder {

        private Video video;

        public Builder withVideo(final Video video) {
            this.video = video;
            return this;
        }

        public CreateVideoResponse build() {
            return new CreateVideoResponse(this);
        }
    }
}

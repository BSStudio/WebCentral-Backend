package hu.bme.sch.bss.webcentral.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.model.Video;

import java.util.List;

@JsonSerialize
public final class VideoListResponse {

    private final Video[] videos;

    public VideoListResponse(final Builder builder) {
        this.videos = builder.videos;
    }

    public Video[] getVideos() {
        return videos;
    }

    public static Builder builder() {
        return new Builder();
    }

    @SuppressWarnings("hiddenfield")
    public static final class Builder {

        private Video[] videos;

        public Builder withVideos(final Video[] videos) {
            this.videos = videos;
            return this;
        }

        public Builder withVideos(final List<Video> videos) {
            this.videos = new Video[videos.size()];
            this.videos = videos.toArray(this.videos);
            return this;
        }

        public VideoListResponse build() {
            return new VideoListResponse(this);
        }
    }
}

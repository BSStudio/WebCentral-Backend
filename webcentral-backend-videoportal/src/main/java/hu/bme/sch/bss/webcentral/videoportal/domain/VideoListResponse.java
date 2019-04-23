package hu.bme.sch.bss.webcentral.videoportal.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.videoportal.model.Video;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonSerialize
public final class VideoListResponse {

    private final VideoResponse[] videos;

    public VideoListResponse(final Builder builder) {
        this.videos = builder.videos;
    }

    public VideoResponse[] getVideos() {
        return videos;
    }

    public static Builder builder() {
        return new Builder();
    }

    // Generated code begins here

    @Override
    public String toString() {
        return "VideoListResponse{"
            + "videos=" + Arrays.toString(videos)
            + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VideoListResponse that = (VideoListResponse) o;
        return Arrays.equals(videos, that.videos);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(videos);
    }

    // Generated code ends here

    @SuppressWarnings("hiddenfield")
    public static final class Builder {

        private VideoResponse[] videos;

        public Builder withVideos(final List<Video> videos) {
            ArrayList<VideoResponse> videoList = new ArrayList<>();
            for (Video video : videos) {
                videoList.add(new VideoResponse(video));
            }
            this.videos = new VideoResponse[videoList.size()];
            this.videos = videoList.toArray(this.videos);
            return this;
        }

        public VideoListResponse build() {
            return new VideoListResponse(this);
        }
    }
}

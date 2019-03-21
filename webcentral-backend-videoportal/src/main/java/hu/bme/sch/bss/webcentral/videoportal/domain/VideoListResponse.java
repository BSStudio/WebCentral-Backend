package hu.bme.sch.bss.webcentral.videoportal.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.videoportal.model.Video;

import java.util.ArrayList;
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

    @SuppressWarnings("hiddenfield")
    public static final class Builder {

        private VideoResponse[] videos;

        public Builder withVideos(final List<Video> videos) {
            ArrayList<VideoResponse> videoList = new ArrayList<>();
            for (Video video: videos){
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

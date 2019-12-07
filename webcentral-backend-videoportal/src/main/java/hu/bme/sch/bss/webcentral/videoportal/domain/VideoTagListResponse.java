package hu.bme.sch.bss.webcentral.videoportal.domain;

import hu.bme.sch.bss.webcentral.videoportal.model.VideoTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VideoTagListResponse {

    private final VideoTagResponse[] videoTags;

    public VideoTagListResponse(final Builder builder) {
        this.videoTags = builder.videoTags;
    }

    public VideoTagResponse[] getVideoTags() {
        return videoTags;
    }

    public static Builder builder() {
        return new Builder();
    }

    // Generated code begins here

    @Override
    public String toString() {
        return "VideoTagResponse{"
                + "videoTags=" + Arrays.toString(videoTags)
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
        VideoTagListResponse that = (VideoTagListResponse) o;
        return Arrays.equals(videoTags, that.videoTags);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(videoTags);
    }

    // Generated code ends here

    @SuppressWarnings("hiddenfield")
    public static final class Builder {

        private VideoTagResponse[] videoTags;

        public Builder withVideoTags(final List<VideoTag> videoTags) {
            ArrayList<VideoTagResponse> videoTagList = new ArrayList<>();
            for (VideoTag videoTag : videoTags) {
                videoTagList.add(new VideoTagResponse(videoTag));
            }
            this.videoTags = new VideoTagResponse[videoTagList.size()];
            this.videoTags = videoTagList.toArray(this.videoTags);
            return this;
        }

        public VideoTagListResponse build() {
            return new VideoTagListResponse(this);
        }
    }
}

package hu.bme.sch.bss.webcentral.videoportal.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.videoportal.model.Video;
import hu.bme.sch.bss.webcentral.videoportal.model.VideoType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonSerialize
public final class VideoTypeListResponse {

    private final VideoTypeResponse[] videoTypes;

    public VideoTypeListResponse(final Builder builder) {
        this.videoTypes = builder.videoTypes;
    }

    public VideoTypeResponse[] getVideoTypes() {
        return videoTypes;
    }

    public static Builder builder() {
        return new Builder();
    }

    // Generated code begins here

    @Override
    public String toString() {
        return "VideoTypeResponse{"
                + "videoTypes=" + Arrays.toString(videoTypes)
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
        VideoTypeListResponse that = (VideoTypeListResponse) o;
        return Arrays.equals(videoTypes, that.videoTypes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(videoTypes);
    }

    // Generated code ends here

    @SuppressWarnings("hiddenfield")
    public static final class Builder {

        private VideoTypeResponse[] videoTypes;

        public Builder withVideoTypes(final List<VideoType> videoTypes) {
            ArrayList<VideoTypeResponse> videoTypeList = new ArrayList<>();
            for (VideoType videoType : videoTypes) {
                videoTypeList.add(new VideoTypeResponse(videoType));
            }
            this.videoTypes = new VideoTypeResponse[videoTypeList.size()];
            this.videoTypes = videoTypeList.toArray(this.videoTypes);
            return this;
        }

        public VideoTypeListResponse build() {
            return new VideoTypeListResponse(this);
        }
    }
}


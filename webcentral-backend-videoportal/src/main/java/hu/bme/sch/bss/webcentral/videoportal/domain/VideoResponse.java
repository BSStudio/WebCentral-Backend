package hu.bme.sch.bss.webcentral.videoportal.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.videoportal.model.Video;
import hu.bme.sch.bss.webcentral.videoportal.model.VideoTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@JsonSerialize
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName("video")
public final class VideoResponse {
    private final Long id;
    private final String longName;
    private final String canonicalName;
    private final String projectName;
    private final String description;
    private final String videoType;
    private final Set<VideoTag> videoTags;
    private final Boolean visible;
    private final Boolean archived;
    private final String videoLocation;
    private final String imageLocation;

    public VideoResponse(final Video video) {
        this.id = video.getId();
        this.longName = video.getLongName();
        this.canonicalName = video.getCanonicalName();
        this.projectName = video.getProjectName();
        this.description = video.getDescription();
        this.videoType = video.getVideoType().getCanonicalName();
        videoTags = video.getVideoTags();
        this.visible = video.getVisible();
        this.archived = video.getArchived();
        this.videoLocation = video.getVideoLocation();
        this.imageLocation = video.getImageLocation();
    }

    public Long getId() {
        return id;
    }

    public String getLongName() {
        return longName;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoType() {
        return videoType;
    }

    public Set<VideoTag> getVideoTags() {
        return videoTags;
    }

    public Boolean getVisible() {
        return visible;
    }

    public Boolean getArchived() {
        return archived;
    }

    public String getVideoLocation() {
        return videoLocation;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    // Generated code begins here

    @Override
    public String toString() {
        return "VideoResponse{"
            + "id=" + id
            + ", longName='" + longName + '\''
            + ", canonicalName='" + canonicalName + '\''
            + ", projectName='" + projectName + '\''
            + ", description='" + description + '\''
            + ", videoType='" + videoType + '\''
            + ", visible=" + visible
            + ", archived=" + archived
            + ", videoLocation='" + videoLocation + '\''
            + ", imageLocation='" + imageLocation + '\''
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
        VideoResponse response = (VideoResponse) o;
        return Objects.equals(id, response.id)
            && Objects.equals(longName, response.longName)
            && Objects.equals(canonicalName, response.canonicalName)
            && Objects.equals(projectName, response.projectName)
            && Objects.equals(description, response.description)
            && Objects.equals(videoType, response.videoType)
            && Objects.equals(visible, response.visible)
            && Objects.equals(archived, response.archived)
            && Objects.equals(videoLocation, response.videoLocation)
            && Objects.equals(imageLocation, response.imageLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longName, canonicalName, projectName, description, videoType, visible, archived, videoLocation, imageLocation);
    }

    // Generated code ends here
}

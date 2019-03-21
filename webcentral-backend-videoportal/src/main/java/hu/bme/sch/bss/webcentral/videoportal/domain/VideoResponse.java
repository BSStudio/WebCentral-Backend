package hu.bme.sch.bss.webcentral.videoportal.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.videoportal.model.Video;

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
}

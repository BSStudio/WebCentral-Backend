package hu.bme.sch.bss.webcentral.videoportal.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.videoportal.model.VideoTag;
import hu.bme.sch.bss.webcentral.videoportal.model.VideoType;

@JsonSerialize
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName("videoTag")
public class VideoTagResponse {
    private final Long id;
    private final String longName;
    private final String canonicalName;
    private final String description;
    private final Boolean archived;

    public VideoTagResponse(final VideoTag VideoTag) {
        this.id = VideoTag.getId();
        this.longName = VideoTag.getLongName();
        this.canonicalName = VideoTag.getCanonicalName();
        this.description = VideoTag.getDescription();
        this.archived = VideoTag.getArchived();
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

    public String getDescription() {
        return description;
    }

    public Boolean getArchived() {
        return archived;
    }
}

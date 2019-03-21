package hu.bme.sch.bss.webcentral.videoportal.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.bme.sch.bss.webcentral.videoportal.model.VideoType;

@JsonSerialize
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName("videoType")
public final class VideoTypeResponse {
    private final Long id;
    private final String longName;
    private final String canonicalName;
    private final String description;
    private final Boolean archived;

    public VideoTypeResponse(final VideoType videoType) {
        this.id = videoType.getId();
        this.longName = videoType.getLongName();
        this.canonicalName = videoType.getCanonicalName();
        this.description = videoType.getDescription();
        this.archived = videoType.getArchived();
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

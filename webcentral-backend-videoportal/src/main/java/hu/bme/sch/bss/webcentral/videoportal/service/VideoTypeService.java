package hu.bme.sch.bss.webcentral.videoportal.service;

import hu.bme.sch.bss.webcentral.videoportal.dao.VideoTypeDao;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoTypeRequest;
import hu.bme.sch.bss.webcentral.videoportal.model.VideoType;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("designforextension")
public class VideoTypeService {
    private static final String VIDEO_TYPE_CREATE_STARTED = "Video type creation started. {}";
    private static final String VIDEO_TYPE_CREATE_SUCCEED = "Video type creation succeed. Created {}";

    private final VideoTypeDao videoTypeDao;
    private final Logger logger;

    public VideoTypeService(final VideoTypeDao videoTypeDao, final Logger logger) {
        this.videoTypeDao = videoTypeDao;
        this.logger = logger;
    }

    public VideoType create(final VideoTypeRequest request) {
        logger.info(VIDEO_TYPE_CREATE_STARTED, request);
        VideoType videoType = VideoType.builder()
            .withLongName(request.getLongName())
            .withCanonicalName(request.getCanonicalName())
            .withDescription(request.getDescription())
            .build();
        videoTypeDao.save(videoType);
        logger.info(VIDEO_TYPE_CREATE_SUCCEED, videoType);
        return videoType;
    }

    public VideoType findByCanonicalName(final String canonicalName) {
        Optional<VideoType> videoType = videoTypeDao.findByCanonicalName(canonicalName);
        if (videoType.isEmpty()) {
            logger.warn("Video type not found with canonical name {}", canonicalName);
            throw new NoSuchElementException("Video Type Not Found");
        }
        return videoType.get();
    }

}

package hu.bme.sch.bss.webcentral.videoportal.service;

import hu.bme.sch.bss.webcentral.videoportal.dao.VideoTagDao;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoTagRequest;
import hu.bme.sch.bss.webcentral.videoportal.model.VideoTag;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class VideoTagService {
    private static final String VIDEO_TAG_CREATE_STARTED = "Video tag creation started. {}";
    private static final String VIDEO_TAG_CREATE_SUCCEED = "Video tag creation succeed. Created {}";
    private static final String VIDEO_TAG_NOT_FOUND_WITH_CANONICAL_NAME = "Video tag not found with canonical name {}";
    private static final String VIDEO_TAG_NOT_FOUND = "Video Tag Not Found";

    private final VideoTagDao videoTagDao;
    private final Logger logger;

    public VideoTagService(VideoTagDao videoTagDao, Logger logger) {
        this.videoTagDao = videoTagDao;
        this.logger = logger;
    }

    public VideoTag create(final VideoTagRequest request){
        logger.info(VIDEO_TAG_CREATE_STARTED, request);
        VideoTag videoTag = VideoTag.builder()
                .withLongName(request.getLongName())
                .withCanonicalName(request.getCanonicalName())
                .withDescription(request.getDescription())
                .build();
        videoTagDao.save(videoTag);
        logger.info(VIDEO_TAG_CREATE_SUCCEED, videoTag);
        return videoTag;
    }

    public void update(final VideoTagRequest request, final VideoTag videoTag){
        videoTag.setCanonicalName(request.getCanonicalName());
        videoTag.setDescription(request.getDescription());
        videoTag.setLongName(request.getLongName());
        videoTagDao.save(videoTag);
    }

    public VideoTag findByCanonicalName(final String canonicalName) {
        Optional<VideoTag> videoTag = videoTagDao.findByCanonicalName(canonicalName);
        if (videoTag.isEmpty()) {
            logger.warn(VIDEO_TAG_NOT_FOUND_WITH_CANONICAL_NAME, canonicalName);
            throw new NoSuchElementException(VIDEO_TAG_NOT_FOUND);
        }
        return videoTag.get();
    }

    public List<VideoTag> findAll() {
        return videoTagDao.findAll();
    }

    public void archive(final VideoTag videoTag){
        videoTag.archive();
        videoTagDao.save(videoTag);
    }
}

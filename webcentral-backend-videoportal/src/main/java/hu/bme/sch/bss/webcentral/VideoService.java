package hu.bme.sch.bss.webcentral;

import hu.bme.sch.bss.webcentral.dao.VideoDao;
import hu.bme.sch.bss.webcentral.domain.CreateVideoRequest;
import hu.bme.sch.bss.webcentral.model.Video;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public final class VideoService {

    private final VideoDao videoDao;
    private final Logger logger;

    public VideoService(final VideoDao videoDao, final Logger logger) {
        this.videoDao = videoDao;
        this.logger = logger;
    }

    public Video createVideo(final CreateVideoRequest request) {
        logger.info("Video creation started");
        Video video = Video.builder()
                .withLongName(request.getLongName())
                .withCanonicalName(request.getCanonicalName())
                .withDescription(request.getDescription())
                .withVisible(request.getVisible())
                .build();
        videoDao.save(video);
        logger.info("Video creation succeed");
        return video;
    }
}

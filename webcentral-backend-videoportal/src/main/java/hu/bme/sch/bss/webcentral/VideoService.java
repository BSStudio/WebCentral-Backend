package hu.bme.sch.bss.webcentral;

import hu.bme.sch.bss.webcentral.dao.VideoDao;
import hu.bme.sch.bss.webcentral.domain.CreateVideoRequest;
import hu.bme.sch.bss.webcentral.model.Video;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("designforextension")
public class VideoService {

    private final VideoDao videoDao;
    private final Logger logger;

    VideoService(final VideoDao videoDao, final Logger logger) {
        this.videoDao = videoDao;
        this.logger = logger;
    }

    public Video create(final CreateVideoRequest request) {
        logger.info("Video creation started");
        Video video = Video.builder()
                .withLongName(request.getLongName())
                .withCanonicalName(request.getCanonicalName())
                .withProjectName(request.getProjectName())
                .withDescription(request.getDescription())
                .withVisible(request.getVisible())
                .withVideoLocation(request.getVideoLocation())
                .withImageLocation(request.getImageLocation())
                .build();
        videoDao.save(video);
        logger.info("Video creation succeed");
        return video;
    }

    public List<Video> findPublished() {
        return videoDao.findAllPublished();
    }

    public List<Video> findAll() {
        return videoDao.findAll();
    }
}

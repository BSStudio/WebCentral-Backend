package hu.bme.sch.bss.webcentral.videoportal;

import hu.bme.sch.bss.webcentral.videoportal.dao.VideoDao;
import hu.bme.sch.bss.webcentral.videoportal.domain.CreateVideoRequest;
import hu.bme.sch.bss.webcentral.videoportal.model.Video;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("designforextension")
public class VideoService {

    private static final String CREATE_VIDEO_STARTED = "Video creation started";
    private static final String CREATE_VIDEO_SUCCEED = "Video creation succeed, created {}";
    private static final String PUBLIC_VIDEOS_SEARCH_STARTED = "Search for public videos started";
    private static final String PUBLIC_VIDEOS_SEARCH_SUCCEED =
            "Search for public videos succeed, found {}";
    private static final String ALL_VIDEOS_SEARCH_STARTED = "Search for all videos started";
    private static final String ALL_VIDEOS_SEARCH_SUCCEED =
            "Search for all videos succeed, found {}";
    private static final String VIDEO_SEARCH_STARTED = "Search for videos started";
    private static final String VIDEO_SEARCH_FINISHED = "Search for videos finished, found {}";
    private static final String VIDEO_ARCHIVE_STARTED = "Video archive started";
    private static final String VIDEO_ARCHIVE_SUCCEED = "Video archive succeed, archived {}";

    private final VideoDao videoDao;
    private final Logger logger;

    VideoService(final VideoDao videoDao, final Logger logger) {
        this.videoDao = videoDao;
        this.logger = logger;
    }

    public Video create(final CreateVideoRequest request) {
        logger.info(CREATE_VIDEO_STARTED);
        Video video = createVideoWithRequestData(request);
        videoDao.save(video);
        logger.info(CREATE_VIDEO_SUCCEED, video);
        return video;
    }

    public List<Video> findPublished() {
        logger.info(PUBLIC_VIDEOS_SEARCH_STARTED);
        List<Video> publicVideoList = videoDao.findAllPublished();
        logger.info(PUBLIC_VIDEOS_SEARCH_SUCCEED, publicVideoList);
        return publicVideoList;
    }

    public List<Video> findAll() {
        logger.info(ALL_VIDEOS_SEARCH_STARTED);
        List<Video> videoList = videoDao.findAll();
        logger.info(ALL_VIDEOS_SEARCH_SUCCEED, videoList);
        return videoList;
    }

    public List<Video> findArchived() {
        logger.info(ALL_VIDEOS_SEARCH_STARTED);
        List<Video> archivedList = videoDao.findAllArchived();
        logger.info(ALL_VIDEOS_SEARCH_SUCCEED, archivedList);
        return archivedList;
    }

    public Video findById(final Long id) {
        logger.info(VIDEO_SEARCH_STARTED);
        Optional<Video> video = videoDao.findById(id);
        video.orElseThrow(NoSuchElementException::new);
        logger.info(VIDEO_SEARCH_FINISHED, video);
        return video.get();
    }

    public void archive(final Video video) {
        logger.info(VIDEO_ARCHIVE_STARTED);
        video.archive();
        videoDao.save(video);
        logger.info(VIDEO_ARCHIVE_SUCCEED, video);
    }

    Video createVideoWithRequestData(final CreateVideoRequest request) {
        return Video.builder()
                .withLongName(request.getLongName())
                .withCanonicalName(request.getCanonicalName())
                .withProjectName(request.getProjectName())
                .withDescription(request.getDescription())
                .withVisible(request.getVisible())
                .withVideoLocation(request.getVideoLocation())
                .withImageLocation(request.getImageLocation())
                .build();
    }
}

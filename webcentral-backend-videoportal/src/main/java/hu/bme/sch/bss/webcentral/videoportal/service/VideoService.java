package hu.bme.sch.bss.webcentral.videoportal.service;

import hu.bme.sch.bss.webcentral.videoportal.dao.VideoDao;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoRequest;
import hu.bme.sch.bss.webcentral.videoportal.model.Video;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("designforextension")
public class VideoService {

    private static final String VIDEO_CREATE_STARTED = "Video creation started. {}";
    private static final String VIDEO_CREATE_SUCCEED = "Video creation succeed. Created {}";
    private static final String VIDEO_ARCHIVE_STARTED = "Video archive started. {}";
    private static final String VIDEO_ARCHIVE_SUCCEED = "Video archive succeed. Archived {}";
    private static final String VIDEO_RESTORE_STARTED = "Video restore started. {}";
    private static final String VIDEO_RESTORE_SUCCEED = "Video restore succeed. Restored {}";
    private static final String VIDEO_UPDATE_STARTED = "Video update started. {}";
    private static final String VIDEO_UPDATE_SUCCEED = "Video update succeed. Updated {}";
    private static final String VIDEO_DELETE_STARTED = "Video delete started. {}";
    private static final String VIDEO_DELETE_SUCCEED = "Video delete succeed. Deleted {}";

    private static final String VIDEO_SEARCH_STARTED = "Search for videos started with id {}";
    private static final String VIDEO_SEARCH_FINISHED = "Search for videos finished. Found {}";
    private static final String VIDEOS_PUBLIC_SEARCH_STARTED = "Search for public videos started.";
    private static final String VIDEOS_PUBLIC_SEARCH_SUCCEED = "Search for public videos succeed. Found {}";
    private static final String VIDEOS_ARCHIVED_SEARCH_STARTED = "Search for archived videos started.";
    private static final String VIDEOS_ARCHIVED_SEARCH_SUCCEED = "Search for archived videos succeed. Found {}";
    private static final String VIDEOS_ALL_SEARCH_STARTED = "Search for all videos started.";
    private static final String VIDEOS_ALL_SEARCH_SUCCEED = "Search for all videos succeed. Found {}";

    private final VideoDao videoDao;
    private final Logger logger;

    VideoService(final VideoDao videoDao, final Logger logger) {
        this.videoDao = videoDao;
        this.logger = logger;
    }

    public Video create(final VideoRequest request) {
        logger.info(VIDEO_CREATE_STARTED, request);
        Video video = createVideoWithRequestData(request);
        videoDao.save(video);
        logger.info(VIDEO_CREATE_SUCCEED, video);
        return video;
    }

    public void update(final VideoRequest request, final Video video) {
        logger.info(VIDEO_UPDATE_STARTED);
        video.setCanonicalName(request.getCanonicalName());
        video.setLongName(request.getLongName());
        video.setProjectName(request.getProjectName());
        video.setDescription(request.getDescription());
        video.setVisible(request.getVisible());
        video.setImageLocation(request.getImageLocation());
        video.setVideoLocation(request.getVideoLocation());
        videoDao.save(video);
        logger.info(VIDEO_UPDATE_SUCCEED, video);
    }

    public void archive(final Video video) {
        logger.info(VIDEO_ARCHIVE_STARTED, video);
        video.setArchived(true);
        videoDao.save(video);
        logger.info(VIDEO_ARCHIVE_SUCCEED, video);
    }

    public void restore(final Video video) {
        logger.info(VIDEO_RESTORE_STARTED, video);
        video.setArchived(false);
        videoDao.save(video);
        logger.info(VIDEO_RESTORE_SUCCEED, video);
    }

    public void delete(final Video video) {
        logger.info(VIDEO_DELETE_STARTED, video);
        videoDao.delete(video);
        logger.info(VIDEO_DELETE_SUCCEED, video);
    }

    public Video findById(final Long id) {
        logger.info(VIDEO_SEARCH_STARTED, id);
        Optional<Video> video = videoDao.findById(id);
        if (video.isEmpty()) {
            logger.warn("Video not found with id {}", id);
            throw new NoSuchElementException("Video Not Found");
        }
        logger.info(VIDEO_SEARCH_FINISHED, video);
        return video.get();
    }

    public List<Video> findAll() {
        logger.info(VIDEOS_ALL_SEARCH_STARTED);
        List<Video> videoList = videoDao.findAll();
        logger.info(VIDEOS_ALL_SEARCH_SUCCEED, videoList);
        return videoList;
    }

    public List<Video> findPublished() {
        logger.info(VIDEOS_PUBLIC_SEARCH_STARTED);
        List<Video> publicVideoList = videoDao.findAllPublished();
        logger.info(VIDEOS_PUBLIC_SEARCH_SUCCEED, publicVideoList);
        return publicVideoList;
    }

    public List<Video> findArchived() {
        logger.info(VIDEOS_ARCHIVED_SEARCH_STARTED);
        List<Video> archivedList = videoDao.findAllArchived();
        logger.info(VIDEOS_ARCHIVED_SEARCH_SUCCEED, archivedList);
        return archivedList;
    }

    Video createVideoWithRequestData(final VideoRequest request) {
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

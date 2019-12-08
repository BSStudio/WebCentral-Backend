package hu.bme.sch.bss.webcentral.videoportal.service;

import com.google.common.collect.Sets;
import hu.bme.sch.bss.webcentral.videoportal.dao.VideoDao;
import hu.bme.sch.bss.webcentral.videoportal.domain.VideoRequest;
import hu.bme.sch.bss.webcentral.videoportal.model.Video;
import hu.bme.sch.bss.webcentral.videoportal.model.VideoTag;
import hu.bme.sch.bss.webcentral.videoportal.model.VideoType;

import java.util.*;

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
    private static final String VIDEO_PUBLISH_STARTED = "Video publish started. {}";
    private static final String VIDEO_PUBLISH_SUCCEED = "Video publish succeed. Updated {}";
    private static final String VIDEO_HIDE_STARTED = "Video hide started. {}";
    private static final String VIDEO_HIDE_SUCCEED = "Video hide succeed. Updated {}";
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

    private final VideoTypeService videoTypeService;
    private final VideoTagService videoTagService;
    private final VideoDao videoDao;
    private final Logger logger;

    public VideoService(VideoTypeService videoTypeService, VideoTagService videoTagService, VideoDao videoDao, Logger logger) {
        this.videoTypeService = videoTypeService;
        this.videoTagService = videoTagService;
        this.videoDao = videoDao;
        this.logger = logger;
    }

    public Video create(final VideoRequest request, final VideoType videoType, final Set<VideoTag> videoTags) {
        logger.info(VIDEO_CREATE_STARTED, request);
        Video video = Video.builder()
                .withLongName(request.getLongName())
                .withCanonicalName(request.getCanonicalName())
                .withProjectName(request.getProjectName())
                .withDescription(request.getDescription())
                .withVisible(request.getVisible())
                .withVideoType(videoType)
                .withVideoTags(videoTags)
                .withVideoLocation(request.getVideoLocation())
                .withImageLocation(request.getImageLocation())
                .build();
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

    public void publish(final Video video) {
        logger.info(VIDEO_PUBLISH_STARTED, video);
        video.setVisible(true);
        videoDao.save(video);
        logger.info(VIDEO_PUBLISH_SUCCEED, video);
    }

    public void hide(final Video video) {
        logger.info(VIDEO_HIDE_STARTED, video);
        video.setVisible(false);
        videoDao.save(video);
        logger.info(VIDEO_HIDE_SUCCEED, video);
    }

    public void delete(final Video video) {
        logger.info(VIDEO_DELETE_STARTED, video);
        videoDao.delete(video);
        logger.info(VIDEO_DELETE_SUCCEED, video);
    }

    public void addTag(final Video video, final VideoTag videoTag) {
        video.getVideoTags().add(videoTag);
        videoDao.save(video);
    }

    public void removeTag(final Video video, final VideoTag videoTag) {
        video.getVideoTags().remove(videoTag);
        videoDao.save(video);
    }

    public Video findById(final Long id) {
        logger.info(VIDEO_SEARCH_STARTED, id);
        Optional<Video> video = videoDao.findById(id);
        if (video.isEmpty()) {
            logger.warn("Video not found with id {}", id);
            throw new NoSuchElementException("Video Not Found");
        }
        logger.info(VIDEO_SEARCH_FINISHED, video.get());
        return video.get();
    }

    public List<Video> findAll() {
        logger.info(VIDEOS_ALL_SEARCH_STARTED);
        List<Video> videoList = videoDao.findAll();
        logger.info(VIDEOS_ALL_SEARCH_SUCCEED, videoList);
        return videoList;
    }

    public List<Video> findByType(String type) {
        VideoType videoType = videoTypeService.findByCanonicalName(type);
        List<Video> videoList = new ArrayList<>();
        videoDao.findAll().forEach((video) -> {
            if (video.getVideoType().equals(videoType))
                videoList.add(video);
        });
        return videoList;
    }

    public List<Video> findByTag(String tag) {
        VideoTag videoTag = videoTagService.findByCanonicalName(tag);
        List<Video> videoList = new ArrayList<>();
        videoDao.findAll().forEach((video) -> {
            if (video.getVideoTags().contains(videoTag))
                videoList.add(video);
        });
        return videoList;
    }

    public List<Video> findRelated(final Video video) {
        var videos = videoDao.findAll();
        var relatedVideos = new ArrayList<RelatedVideo>();
        for (int i = 0; i < videos.size(); i++) {
            var point = 0;
            if (video.getVideoType().equals(videos.get(i).getVideoType()))
                point += 1;
            if (video.getProjectName().equals(videos.get(i).getProjectName()))
                point += 3;

            var tags = video.getVideoTags().toArray(new VideoTag[video.getVideoTags().size()]);
            for (int j = 0; j < tags.length; j++) {
                var otherTags = videos.get(i).getVideoTags().toArray(new VideoTag[video.getVideoTags().size()]);
                for (int k = 0; k < otherTags.length; k++) {
                    if(tags[j].getId() == otherTags[k].getId())
                        point += 1;
                }
            }
            relatedVideos.add(new RelatedVideo(videos.get(i), point));
        }



        var results = new ArrayList<Video>();
        var resultsNumber = 10;
        Collections.sort(relatedVideos);

        if (videos.size() < 10)
            resultsNumber = videos.size();
        for (int i = 0; i < resultsNumber; i++) {
            results.add(relatedVideos.get(i).video);
        }

        return results;
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

    private class RelatedVideo implements Comparable<RelatedVideo> {
        private Video video;
        private Integer point;

        public RelatedVideo(Video video, Integer point) {
            this.video = video;
            this.point = point;
        }

        @Override
        public int compareTo(RelatedVideo relatedVideo) {
            return relatedVideo.point - point;
        }
    }

}

package hu.bme.sch.bss.webcentral.videoportal.domain;

import hu.bme.sch.bss.webcentral.videoportal.model.Video;
import hu.bme.sch.bss.webcentral.videoportal.model.VideoType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class VideoListResponseTest {

    @Test
    void testBuilderWithList(){
        // GIVEN
        List<Video> videoList = new ArrayList<>();
        VideoType mockType = mock(VideoType.class);

        Video video1 = Video.builder()
            .withVideoType(mockType)
            .build();
        Video video2 = Video.builder()
            .withVideoType(mockType)
            .build();

        videoList.add(video1);
        videoList.add(video2);

        // WHEN
        VideoListResponse result = VideoListResponse.builder()
                .withVideos(videoList)
                .build();

        // THEN
        assertEquals(new VideoResponse(video1), result.getVideos()[0]);
        assertEquals(new VideoResponse(video2), result.getVideos()[1]);
    }

}

package hu.bme.sch.bss.webcentral.domain;

import hu.bme.sch.bss.webcentral.model.Video;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListVideosResponseTest {

    @Test
    void testBuilderWithList(){
        // GIVEN
        List<Video> videoList = new ArrayList<>();

        Video video1 = Video.builder().build();
        Video video2 = Video.builder().build();

        videoList.add(video1);
        videoList.add(video2);

        // WHEN
        ListVideosResponse result = ListVideosResponse.builder()
                .withVideos(videoList)
                .build();

        // THEN
        assertEquals(videoList, Arrays.asList(result.getVideos()));
    }


    @Test
    void testBuilderWithArray(){
        // GIVEN
        Video[] videos = new Video[2];

        Video video1 = Video.builder().build();
        Video video2 = Video.builder().build();

        videos[0] = video1;
        videos[1] = video2;

        // WHEN
        ListVideosResponse result = ListVideosResponse.builder()
                .withVideos(videos)
                .build();

        // THEN
        assertEquals(videos, result.getVideos());
    }

}

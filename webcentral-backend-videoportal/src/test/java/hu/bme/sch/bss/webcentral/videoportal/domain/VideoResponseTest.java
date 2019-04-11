package hu.bme.sch.bss.webcentral.videoportal.domain;

import hu.bme.sch.bss.webcentral.videoportal.model.Video;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VideoResponseTest {

    @Test
    void testBuilder(){
        // GIVEN
        Video actual = Video.builder().build();

        // WHEN
        VideoResponse result = VideoResponse.builder()
                .withVideo(actual)
                .build();

        // THEN
        assertEquals(actual, result.getVideo());
    }

}

package hu.bme.sch.bss.webcentral.domain;

import hu.bme.sch.bss.webcentral.model.Video;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateVideoResponseTest {

    @Test
    void testBuilder(){
        // GIVEN
        Video actual = Video.builder().build();

        // WHEN
        CreateVideoResponse result = CreateVideoResponse.builder()
                .withVideo(actual)
                .build();

        // THEN
        assertEquals(actual, result.getVideo());
    }

}

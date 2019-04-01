package org.uhafactory.test.tour.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.uhafactory.test.tour.dto.DescriptionKeywordResult;
import org.uhafactory.test.tour.dto.SimpleRegionDto;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TourRestControllerIntegrationTest {
    @Autowired
    private TourRestController controller;

    @Test
    void testGetKeywordCount() {
        String keyword = "세계문화유산";
        Map<String, String> request = new HashMap<>();
        request.put("keyword", keyword);

        ResponseEntity<DescriptionKeywordResult> result = controller.getKeywordCount(request);

        DescriptionKeywordResult keywordResult = result.getBody();
        assertThat(keywordResult.getKeyword()).isEqualTo(keyword);
        assertThat(keywordResult.getPrograms()).hasSize(1);
        assertThat(keywordResult.getPrograms().get(0).getCount()).isEqualTo(2);
        assertThat(keywordResult.getPrograms().get(0).getName()).isEqualTo("경상북도 경주시");
    }

    @Test
    void testGetProgramNameAndTheme() {
        Map<String, String> request = new HashMap<>();
        request.put("region", "평창군");

        ResponseEntity<SimpleRegionDto> result = controller.getProgramNameAndTheme(request);
        SimpleRegionDto searchResult = result.getBody();

        assertThat(searchResult.getRegion()).isNotBlank();
        assertThat(searchResult.getPrograms()).hasSize(4);
        assertThat(searchResult.getPrograms().get(0).getName()).isEqualTo("오감만족! 오대산 에코 어드벤처 투어");
        assertThat(searchResult.getPrograms().get(0).getTheme()).isEqualTo("아동·청소년 체험학습");
        assertThat(searchResult.getPrograms().get(1).getName()).isEqualTo("오대산국립공원 힐링캠프");
        assertThat(searchResult.getPrograms().get(1).getTheme()).isEqualTo("숲 치유,");

    }
}

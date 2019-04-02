package org.uhafactory.tour.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.uhafactory.tour.dto.RegionAndCountResult;
import org.uhafactory.tour.dto.Request;
import org.uhafactory.tour.dto.Result;
import org.uhafactory.tour.dto.SimpleRegionDto;
import org.uhafactory.tour.program.recommend.RecommendationRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TourRestControllerIntegrationTest {
    @Autowired
    private TourRestController controller;

    @Test
    void testGetRegionAndCount() {
        Request.KeywordDto keywordDto = new Request.KeywordDto("세계문화유산");
        ResponseEntity<RegionAndCountResult> result = controller.getRegionAndCount(new Request.KeywordDto("세계문화유산"));

        RegionAndCountResult keywordResult = result.getBody();
        assertThat(keywordResult.getKeyword()).isEqualTo(keywordDto.getKeyword());
        assertThat(keywordResult.getPrograms()).hasSize(1);
        assertThat(keywordResult.getPrograms().get(0).getCount()).isEqualTo(2);
        assertThat(keywordResult.getPrograms().get(0).getName()).isEqualTo("경상북도 경주시");
    }

    @Test
    void testGetProgramNameAndTheme() {
        ResponseEntity<SimpleRegionDto> result = controller.getProgramNameAndTheme(new Request.RegionNameDto("평창군"));
        SimpleRegionDto searchResult = result.getBody();

        assertThat(searchResult.getRegion()).isNotBlank();
        assertThat(searchResult.getPrograms()).hasSize(4);
        assertThat(searchResult.getPrograms().get(0).getName()).isEqualTo("오감만족! 오대산 에코 어드벤처 투어");
        assertThat(searchResult.getPrograms().get(0).getTheme()).isEqualTo("아동·청소년 체험학습");
        assertThat(searchResult.getPrograms().get(1).getName()).isEqualTo("오대산국립공원 힐링캠프");
        assertThat(searchResult.getPrograms().get(1).getTheme()).isEqualTo("숲 치유,");
    }

    @Test
    void testGetKeywordCount() {
        ResponseEntity<Result.KeywordAndCountDto> result = controller.getKeywordCount(new Request.KeywordDto("체험"));
        Result.KeywordAndCountDto searchResult = result.getBody();
        assertThat(searchResult.getKeyword()).isEqualTo("체험");
        assertThat(searchResult.getCount()).isEqualTo(99);

        result = controller.getKeywordCount(new Request.KeywordDto("입체적"));
        searchResult = result.getBody();
        assertThat(searchResult.getCount()).isEqualTo(1);
    }

    @Test
    void testRecommendProgram() {
        RecommendationRequest request = new RecommendationRequest();
        request.setRegion("남해군");
        request.setKeyword("생태체험");

        BindingResult bindingResult = mock(BindingResult.class);
        given(bindingResult.hasErrors()).willReturn(false);

        ResponseEntity<Result.ProgramIdDto> result = controller.getRecommendProgram(request, bindingResult);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        Result.ProgramIdDto programIdDto = result.getBody();
        assertThat(programIdDto.getProgram()).isNotBlank();
    }
}

package org.uhafactory.tour.api;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.uhafactory.tour.dto.RegionAndCountResult;
import org.uhafactory.tour.dto.Request;
import org.uhafactory.tour.dto.Result;
import org.uhafactory.tour.dto.SimpleRegionDto;
import org.uhafactory.tour.program.recommend.RecommendationRequest;

import static org.assertj.core.api.Assertions.assertThat;

public class TourRestControllerIntegrationTest extends IntegrationTest {
    @Test
    void testGetRegionAndCount() {
        Request.KeywordDto keywordDto = new Request.KeywordDto("세계문화유산");

        ResponseEntity<RegionAndCountResult> result = testRestTemplate.postForEntity(
                createURL("/tour/regions/search/regions_count"), new Request.KeywordDto("세계문화유산"), RegionAndCountResult.class);

        RegionAndCountResult keywordResult = result.getBody();
        assertThat(keywordResult.getKeyword()).isEqualTo(keywordDto.getKeyword());
        assertThat(keywordResult.getPrograms()).hasSize(2);
        assertThat(keywordResult.getPrograms().get(0).getCount()).isEqualTo(2);
        assertThat(keywordResult.getPrograms().get(0).getName()).isIn("경상북도", "경상북도 경주시");
    }

    @Test
    void testGetProgramNameAndTheme() {
        ResponseEntity<SimpleRegionDto> result = testRestTemplate.postForEntity(
                createURL("/tour/regions/search/program_themes"), new Request.RegionNameDto("평창군"), SimpleRegionDto.class);
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
        ResponseEntity<Result.KeywordAndCountDto> result = testRestTemplate.postForEntity(
                createURL("/tour/regions/search/keyword_count"), new Request.KeywordDto("체험"), Result.KeywordAndCountDto.class);
        Result.KeywordAndCountDto searchResult = result.getBody();
        assertThat(searchResult.getKeyword()).isEqualTo("체험");
        assertThat(searchResult.getCount()).isEqualTo(99);
    }

    @Test
    void testRecommendProgram() {
        RecommendationRequest request = new RecommendationRequest();
        request.setRegion("남해군");
        request.setKeyword("생태체험");

        ResponseEntity<Result.ProgramIdDto> result = testRestTemplate.postForEntity(
                createURL("/tour/programs/recommend"), request, Result.ProgramIdDto.class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        Result.ProgramIdDto programIdDto = result.getBody();
        assertThat(programIdDto.getProgram()).isNotBlank();
    }


}

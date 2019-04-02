package org.uhafactory.tour.api;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.uhafactory.tour.dto.ProgramDto;

import static org.assertj.core.api.Assertions.assertThat;

class ProgramRestControllerIntegrationTest extends IntegrationTest {

    @Disabled("필요시 단독으로 테스트 실행")
    @Test
    void testImport() {
        DataFile dataFile = DataFile.create("classpath:data/insert.csv");

        ResponseEntity<Void> result = testRestTemplate.postForEntity(
                createURL("/tour/programs/import"), dataFile, Void.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testCreate_select_update_delete() {
        ProgramDto programDto = new ProgramDto();
        programDto.setName("hello");
        programDto.setDescription("desc");
        programDto.setServiceArea("강원도 평창");
        programDto.setTheme("theme");

        ResponseEntity<String> result = testRestTemplate.postForEntity(createURL("/tour/programs"), programDto, String.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotBlank();

        String id = result.getBody();

        ResponseEntity<ProgramDto> select = testRestTemplate.getForEntity(createURL("/tour/programs/" + id), ProgramDto.class);
        ProgramDto selectedProgram = select.getBody();
        assertThat(selectedProgram.getName()).isEqualTo(programDto.getName());

        programDto.setId(selectedProgram.getId());
        programDto.setName("changed name");

        result = testRestTemplate.exchange(createURL("/tour/programs"), HttpMethod.PUT, new HttpEntity<>(programDto), String.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotBlank();

        select = testRestTemplate.getForEntity(createURL("/tour/programs/" + id), ProgramDto.class);
        selectedProgram = select.getBody();
        assertThat(selectedProgram.getName()).isEqualTo("changed name");

        testRestTemplate.delete(createURL("/tour/programs/"+ id));

        select = testRestTemplate.getForEntity(createURL("/tour/programs/" + id), ProgramDto.class);
        assertThat(select.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }
}

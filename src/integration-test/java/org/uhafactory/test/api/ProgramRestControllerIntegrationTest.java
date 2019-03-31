package org.uhafactory.test.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.uhafactory.test.api.dto.ProgramDto;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProgramRestControllerIntegrationTest {

    @Autowired
    private ProgramRestController controller;

    @Test
    void test() throws IOException {
        DataFile dataFile = DataFile.create("classpath:data/insert.csv");

        ResponseEntity<Void> result = controller.create(dataFile);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testCreate_select_delete() {
        ProgramDto programDto = new ProgramDto();
        programDto.setName("hello");
        programDto.setDescription("desc");
        programDto.setServiceArea("강원도 평창");
        programDto.setTheme("theme");

        ResponseEntity<String> result = controller.create(programDto);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isNotBlank();

        String id = result.getBody();

        ResponseEntity<ProgramDto> select = controller.get(id);
        ProgramDto selectedProgram = select.getBody();
        assertThat(selectedProgram.getName()).isEqualTo(programDto.getName());

        ResponseEntity<Void> deleteResult = controller.delete(id);
        assertThat(deleteResult.getStatusCode()).isEqualTo(HttpStatus.OK);

        select = controller.get(id);
        assertThat(select.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }
}

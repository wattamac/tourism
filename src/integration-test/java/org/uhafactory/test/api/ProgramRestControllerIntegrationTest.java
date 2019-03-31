package org.uhafactory.test.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
}

package org.uhafactory.test.tour.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.uhafactory.test.tour.program.Program;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ProgramCsvReaderTest {
    private ProgramCsvReader csvReader = new ProgramCsvReader();

    // "16,숲과 테마가 있는 우이령으로의 여행,\"문화생태체험,\",경기도 양주시 교현리,문화생태체험, 문화생태체험"
    @Test
    void testCsvFileRead() throws IOException {

        Resource resource = new ClassPathResource("data/insert.csv");

        List<Program> result = csvReader.read(resource);
        assertThat(result).hasSize(110);
        assertThat(result.get(15).getName()).isEqualTo("숲과 테마가 있는 우이령으로의 여행");
        assertThat(result.get(15).getTheme()).isEqualTo("문화생태체험,");
        assertThat(result.get(15).getServiceArea()).isEqualTo("경기도 양주시 교현리");
        assertThat(result.get(15).getDescription()).isEqualTo("문화생태체험");
        assertThat(result.get(15).getDetailDescription()).isEqualTo("문화생태체험");
    }

    @Disabled
    @Test
    void testGetServiceArea() throws IOException {
        Resource resource = new ClassPathResource("data/insert.csv");

        List<Program> result = csvReader.read(resource);

        String serviceArea = result.stream()
                .map(p -> p.getServiceArea()).collect(Collectors.joining("\n"));

        log.info("serviceArea = {}", serviceArea);

    }
}
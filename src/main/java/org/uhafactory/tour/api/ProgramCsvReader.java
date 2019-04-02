package org.uhafactory.tour.api;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.uhafactory.tour.program.Program;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ProgramCsvReader {
    @Autowired
    private ApplicationContext context;

    public List<Program> read(Resource resource) throws IOException {
        List<String[]> loadData = loadFile(resource.getURL());
        return loadData.stream()
                .map(this::convert).collect(Collectors.toList());
    }

    public List<Program> read(DataFile dataFile) throws IOException {
        return read(context.getResource(dataFile.getResource()));
    }

    private Program convert(String[] array) {
        return Program.builder()
                .name(array[1])
                .theme(array[2].trim())
                .serviceArea(array[3].trim())
                .description(array[4].trim())
                .detailDescription(array[5].trim())
                .build();
    }


    public List<String[]> loadFile(URL url) {
        try {
            CsvMapper mapper = new CsvMapper();
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withSkipFirstDataRow(true);
            mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
            MappingIterator<String[]> readValues =
                    mapper.readerFor(String[].class).with(bootstrapSchema).readValues(url);
            return readValues.readAll();
        } catch (Exception e) {
            log.error(
                    "Error occurred while loading many to many relationship from file = " + url.getPath(), e);
            return Collections.emptyList();
        }
    }
}

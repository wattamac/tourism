package org.uhafactory.test.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.uhafactory.test.tourism.program.Program;
import org.uhafactory.test.tourism.program.ProgramService;

import java.io.IOException;
import java.util.List;

@RestController("/tour/programs")
public class ProgramRestController {

    @Autowired
    private ProgramCsvReader programCsvReader;

    @Autowired
    private ProgramService programService;

    @GetMapping("/")
    @ResponseBody
    public String test() {
        return "hello";
    }

    @PostMapping
    public ResponseEntity<Void> create(DataFile dataFile) throws IOException {
        List<Program> programs = programCsvReader.read(dataFile);

        programService.create(programs);
        return ResponseEntity.ok().build();
    }



}


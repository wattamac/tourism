package org.uhafactory.test.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uhafactory.test.api.dto.DtoMapper;
import org.uhafactory.test.api.dto.ProgramDto;
import org.uhafactory.test.tourism.program.Program;
import org.uhafactory.test.tourism.program.ProgramService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController("/tour/programs")
public class ProgramRestController {

    @Autowired
    private ProgramCsvReader programCsvReader;

    @Autowired
    private ProgramService programService;

    @PostMapping("/import")
    public ResponseEntity<Void> create(DataFile dataFile) throws IOException {
        List<Program> programs = programCsvReader.read(dataFile);

        programService.create(programs);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @PutMapping
    public ResponseEntity<String> create(ProgramDto request) {
        Program program = DtoMapper.MAPPER.toEntity(request);

        programService.save(program);

        return ResponseEntity.ok(program.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramDto> get(@PathVariable("id") String id) {
        try {
            Optional<Program> program = programService.getOne(id);
            if (program.isPresent()) {
                return ResponseEntity.ok(DtoMapper.MAPPER.toDto(program.get()));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        try {
            programService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


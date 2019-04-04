package org.uhafactory.tour.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.uhafactory.tour.api.dto.DataFile;
import org.uhafactory.tour.api.dto.DtoMapper;
import org.uhafactory.tour.api.dto.ProgramDto;
import org.uhafactory.tour.program.Program;
import org.uhafactory.tour.program.ProgramService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/tour/programs")
public class ProgramRestController {

    @Autowired
    private ProgramCsvReader programCsvReader;

    @Autowired
    private ProgramService programService;

    @PostMapping("/import")
    public ResponseEntity<Void> create(@RequestBody DataFile dataFile) throws IOException {
        List<Program> programs = programCsvReader.read(dataFile);

        programService.create(programs);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<String> create(@Valid @RequestBody ProgramDto request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Program program = DtoMapper.MAPPER.toEntity(request);

        programService.save(program);

        return ResponseEntity.ok(program.getId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramDto> get(@PathVariable("id") String id) {
        try {
            Optional<Program> program = programService.getProgramById(id);

            return program.map(r -> ResponseEntity.ok(DtoMapper.MAPPER.toDto(program.get())))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

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


package org.uhafactory.test.tourism.program;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uhafactory.test.tourism.region.RegionService;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@Service
public class ProgramService {
    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private RegionService regionService;

    @Transactional
    public void create(List<Program> programs) {
        for(Program program : programs) {
            save(program);
        }
    }

    @Transactional
    public void save(Program program) {
        String serviceArea = program.getServiceArea();
        program.setRegions(regionService.getRegions(serviceArea));

        log.info("create program : {}", program);
        programRepository.save(program);
    }
}

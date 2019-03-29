package org.uhafactory.test.tourism.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uhafactory.test.tourism.Program;
import org.uhafactory.test.tourism.ProgramRepository;
import org.uhafactory.test.tourism.Region;

@Service
public class ProgramCreator {
    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private RegionService regionService;

    public Program create(ProgramInput input) {

        Program program = convert(input);
        return programRepository.save(program);
    }

    Program convert(ProgramInput input) {
        Region region = regionService.getRegion(input.getRegion());

        return Program.builder()
                .name(input.getName())
                .theme(input.getTheme())
                .region(region)
                .descrition(input.getDescrition())
                .detailDescription(input.getDetailDescription())
                .build();
    }
}

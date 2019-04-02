package org.uhafactory.tour.program;

import com.mysema.commons.lang.CloseableIterator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uhafactory.tour.Keyword;
import org.uhafactory.tour.program.region.RegionService;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.StreamSupport;

import static java.util.Spliterators.spliteratorUnknownSize;

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

    public Optional<Program> getOne(String id) {
        return programRepository.findById(id);
    }

    @Transactional
    public void deleteById(String id) {
        programRepository.deleteById(id);
    }

    public int countKeywordAllProgramDescription(String keyword) {
        Keyword keywordCounter = new Keyword(keyword);
        try (CloseableIterator<Program> iterator = programRepository.getAll()) {
            return StreamSupport.stream(spliteratorUnknownSize(iterator, Spliterator.NONNULL), false)
                    .map(p -> keywordCounter.matchCount(p.getDetailDescription()))
                    .reduce(Integer::sum)
                    .orElse(0);
        }
    }
}

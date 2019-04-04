package org.uhafactory.tour.program;

import com.google.common.collect.Lists;
import com.mysema.commons.lang.CloseableIterator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.uhafactory.tour.program.region.Region;
import org.uhafactory.tour.program.region.RegionService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProgramServiceTest {
    @InjectMocks
    private ProgramService programService;

    @Mock
    private ProgramRepository programRepository;

    @Mock
    private RegionService regionService;

    @Test
    void testCreate() {
        String serviceArea = "service";
        Region region = new Region();
        given(regionService.getRegions(serviceArea)).willReturn(Lists.newArrayList(region));

        programService.create(Lists.newArrayList(Program.builder().serviceArea(serviceArea).name("1").build()));

        ArgumentCaptor<Program> argumentCaptor = ArgumentCaptor.forClass(Program.class);
        verify(programRepository).save(argumentCaptor.capture());

        Program savedProgram = argumentCaptor.getValue();
        assertThat(savedProgram.getRegions()).containsOnly(region);
    }

    @Test
    void testCountKeywordAllProgramDescription() {
        String keyword = "keyword";

        CloseableIterator<Program> iterator = new CloseableIterator<Program>() {
            private int total = 3;
            private int count = 0;
            @Override
            public void close() {

            }

            @Override
            public boolean hasNext() {
                return count++ < total;
            }

            @Override
            public Program next() {
                return Program.builder().detailDescription("keyword").build();
            }
        };

        given(programRepository.getAll()).willReturn(iterator);

        int result = programService.countKeywordAllProgramDescription(keyword);
        assertThat(result).isEqualTo(3);
    }

    @Test
    void testGetProgram() {
        String id = "programId";
        Optional<Program> expectedResult = Optional.of(Program.builder().build());
        given(programRepository.findById(id)).willReturn(expectedResult);

        Optional<Program> result = programService.getProgramById(id);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDeleteById() {
        String programId = "id";
        programService.deleteById(programId);

        verify(programRepository).deleteById(programId);
    }
}
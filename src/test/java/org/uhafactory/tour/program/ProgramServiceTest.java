package org.uhafactory.tour.program;

import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.uhafactory.tour.program.region.Region;
import org.uhafactory.tour.program.region.RegionService;

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
        Assertions.assertThat(savedProgram.getRegions()).containsOnly(region);
    }
}
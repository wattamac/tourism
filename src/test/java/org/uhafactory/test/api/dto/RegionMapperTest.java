package org.uhafactory.test.api.dto;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.uhafactory.test.tourism.program.Program;
import org.uhafactory.test.tourism.region.Region;

import static org.assertj.core.api.Assertions.assertThat;

class RegionMapperTest {

    @Test
    void testToRegionDto() {
        Region region = Region.create("강원도");
        region.setCode("code");
        region.setPrograms(Lists.newArrayList(
                Program.builder().name("강원도").theme("theme")
                        .serviceArea("serviceArea")
                        .description("description")
                        .detailDescription("Detail")
                        .build())
        );

        RegionDto dto = RegionMapper.MAPPER.toRegionDto(region);
        assertThat(dto.getRegion()).isEqualTo("code");
        assertThat(dto.getPrograms()).hasSize(1);
        assertThat(dto.getPrograms().get(0).getName()).isEqualTo("강원도");
    }

    @Test
    void testProgram() {
        Program program = Program.builder().name("강원도").theme("theme")
                .serviceArea("serviceArea")
                .description("description")
                .detailDescription("Detail")
                .build();

        ProgramDto dto = RegionMapper.MAPPER.toProgramDto(program);
        assertThat(dto.getName()).isEqualTo(program.getName());
        assertThat(dto.getTheme()).isEqualTo(program.getTheme());
        assertThat(dto.getDescription()).isEqualTo(program.getDescription());
        assertThat(dto.getDetailDescription()).isEqualTo(program.getDetailDescription());
    }


}
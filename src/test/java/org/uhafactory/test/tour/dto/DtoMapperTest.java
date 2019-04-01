package org.uhafactory.test.tour.dto;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.uhafactory.test.tour.program.Program;
import org.uhafactory.test.tour.region.Region;

import static org.assertj.core.api.Assertions.assertThat;

class DtoMapperTest {

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

        RegionDto dto = DtoMapper.MAPPER.toDto(region);
        assertThat(dto.getRegion()).isEqualTo("code");
        assertThat(dto.getPrograms()).hasSize(1);
        assertThat(dto.getPrograms().get(0).getName()).isEqualTo("강원도");
    }

    @Test
    void testToProgramDto() {
        Program program = Program.builder().name("강원도").theme("theme")
                .serviceArea("serviceArea")
                .description("description")
                .detailDescription("Detail")
                .build();

        ProgramDto dto = DtoMapper.MAPPER.toDto(program);
        assertThat(dto.getName()).isEqualTo(program.getName());
        assertThat(dto.getTheme()).isEqualTo(program.getTheme());
        assertThat(dto.getDescription()).isEqualTo(program.getDescription());
        assertThat(dto.getDetailDescription()).isEqualTo(program.getDetailDescription());
    }

    @Test
    void testSimpleDto() {
        Region region = Region.create("강원도");
        region.setCode("REG1213");

        region.setPrograms(Lists.newArrayList(
                Program.builder().name("강원도").theme("theme")
                        .build())
        );

        SimpleRegionDto dto = DtoMapper.MAPPER.toSimpleDto(region);
        assertThat(dto.getRegion()).isEqualTo(region.getCode());
        assertThat(dto.getPrograms()).hasSize(1);
        assertThat(dto.getPrograms().get(0).getName()).isEqualTo("강원도");
        assertThat(dto.getPrograms().get(0).getTheme()).isEqualTo("theme");
    }
}
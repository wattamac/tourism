package org.uhafactory.tour.program.region;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.uhafactory.tour.RepositoryTestBase;
import org.uhafactory.tour.dto.RegionAndCountResult;
import org.uhafactory.tour.program.Program;
import org.uhafactory.tour.program.ProgramRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RegionRepositoryTest extends RepositoryTestBase {

    @Autowired
    private RegionRepository repository;

    @Autowired
    private ProgramRepository programRepository;

    @Test
    void testFindByNames() {
        persist(Region.create("강원도 속초시", Lists.newArrayList("강원 속초", "속초")));
        persist(Region.create("강원도 평창군", Lists.newArrayList("평창")));
        persist(Region.create("강원도 양평군"));
        persist(Region.create("강원도", Lists.newArrayList("강원도", "강원")));

        List<Region> result = repository.findByNames(Lists.newArrayList("강원", "평창", "속초"));

        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getName());
    }

    @Test
    void testFindByName() {
        persist(Region.create("강원도 속초시"));
        persist(Region.create("강원도 양평군"));
        Region region1 = persist(Region.create("강원도 평창군", Lists.newArrayList("평창군", "강원도 평창군")));
        Region region2 = persist(Region.create("강원도"));

        persist(Program.builder().name("1").theme("theme1").regions(Lists.newArrayList(region1, region2)).build());
        persist(Program.builder().name("2").theme("theme2").regions(Lists.newArrayList(region1, region2)).build());

        flushAndClear();

        Region result = repository.findByNameWithPrograms("평창군");

        assertThat(result.getCode()).isEqualTo(region1.getCode());
        assertThat(result.getPrograms()).hasSize(2);
        assertThat(result.getPrograms().get(0).getName()).isEqualTo("1");
        assertThat(result.getPrograms().get(1).getName()).isEqualTo("2");
    }

    @Test
    void testGetOneWithProgram() {
        Region region = Region.create("강원도 속초시");
        persist(region);
        persist(Region.create("강원도 평창군"));

        Region result = repository.findByCodeWithPrograms(region.getCode());
        assertThat(result.getName()).isEqualTo("강원도 속초시");
    }

    @Test
    void testFindByProgramDescriptionKeyword() {
        String keyword = "세계문화유산";

        Region region1 = persist(Region.create("강원도 평창군"));
        Region region2 = persist(Region.create("강원도 양평군"));
        Region region3 = persist(Region.create("강원도 속초시"));

        persist(Program.builder().name("1").description("세계문화유산을 경험").regions(Lists.newArrayList(region1, region2)).build());
        persist(Program.builder().name("2").description("N 세계문화유산을 경험").regions(Lists.newArrayList(region1)).build());
        persist(Program.builder().name("3").description("NO").regions(Lists.newArrayList(region1, region2)).build());
        persist(Program.builder().name("4").description("NO").regions(Lists.newArrayList(region1)).build());

        persist(Program.builder().name("5").description("세계문화유산을 경험").regions(Lists.newArrayList(region3)).build());
        persist(Program.builder().name("6").description("세계문화유산을 경험").regions(Lists.newArrayList(region3)).build());
        persist(Program.builder().name("7").description("세계문화유산을 경험하자").regions(Lists.newArrayList(region3)).build());

        flushAndClear();

        List<RegionAndCountResult.RegionAndProgramCount> regions = repository.findByProgramInContainsDescriptionKeyword(keyword);
        assertThat(regions).hasSize(3);
        assertThat(regions.get(0).getCount()).isEqualTo(3);
        assertThat(regions.get(0).getName()).isEqualTo(region3.getName());
        assertThat(regions.get(1).getCount()).isEqualTo(2);
        assertThat(regions.get(1).getName()).isEqualTo(region1.getName());
        assertThat(regions.get(2).getCount()).isEqualTo(1);
        assertThat(regions.get(2).getName()).isEqualTo(region2.getName());
    }

    private Region persist(Region region) {
        return repository.save(region);
    }

    private void persist(Program program) {
        programRepository.save(program);
    }
}
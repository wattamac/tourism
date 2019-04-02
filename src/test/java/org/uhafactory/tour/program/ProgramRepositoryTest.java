package org.uhafactory.tour.program;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.uhafactory.tour.RepositoryTestBase;
import org.uhafactory.tour.program.region.Region;
import org.uhafactory.tour.program.region.RegionRepository;

import static org.assertj.core.api.Assertions.assertThat;

class ProgramRepositoryTest extends RepositoryTestBase {
    @Autowired
    private ProgramRepository repository;

    @Autowired
    private RegionRepository regionRepository;

    @Test
    void testMapping() {
        Region region1 = persist(Region.create("강원도"));
        Region region2 = persist(Region.create("원주시"));
        Region region3 = persist(Region.create("통영시"));

        Program program = new Program();
        program.setName("자연과 문화를 함께 즐기는 설악산 기행");
        program.setTheme("문화생태체험,자연생태체험,");
        program.setServiceArea("강원도 속초");
        program.setRegions(Lists.newArrayList(region1, region2));

        repository.save(program);

        flushAndClear();

        Program result = repository.getOne(program.getId());

        assertThat(result.getName()).isEqualTo(program.getName());
        Assertions.assertThat(result.getRegions()).hasSize(2);
        Assertions.assertThat(result.getRegions().get(0).getName().getName()).isEqualTo("강원도");

        Region region = regionRepository.getOne(region1.getCode());
        Assertions.assertThat(region.getPrograms()).hasSize(1);

        result.setRegions(Lists.newArrayList(region3));

        repository.save(result);

        flushAndClear();

        result = repository.getOne(program.getId());
        Assertions.assertThat(result.getRegions()).containsOnly(region3);
    }

    private Region persist(Region region) {
        return regionRepository.save(region);
    }
}
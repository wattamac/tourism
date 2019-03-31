package org.uhafactory.test.tourism.program;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.uhafactory.test.RepositoryTestBase;
import org.uhafactory.test.tourism.region.Region;
import org.uhafactory.test.tourism.region.RegionRepository;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

class ProgramRepositoryTest extends RepositoryTestBase {
    @Autowired
    private ProgramRepository repository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private EntityManager entityManager;

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

        entityManager.flush();
        entityManager.clear();

        Program result = repository.getOne(program.getId());

        assertThat(result.getName()).isEqualTo(program.getName());
        assertThat(result.getRegions()).hasSize(2);
        assertThat(result.getRegions().get(0).getName().getName()).isEqualTo("강원도");

        Region region = regionRepository.getOne(region1.getCode());
        assertThat(region.getPrograms()).hasSize(1);

        result.setRegions(Lists.newArrayList(region3));

        repository.save(result);

        entityManager.flush();
        entityManager.clear();

        result = repository.getOne(program.getId());
        assertThat(result.getRegions()).containsOnly(region3);
    }

    private Region persist(Region region) {
        return regionRepository.save(region);
    }
}
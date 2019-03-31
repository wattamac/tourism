package org.uhafactory.test.tourism;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.uhafactory.test.RepositoryTestBase;
import org.uhafactory.test.tourism.region.Region;
import org.uhafactory.test.tourism.region.RegionRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RegionRepositoryTest extends RepositoryTestBase {

    @Autowired
    private RegionRepository repository;

    @Test
    void testFindByNames() {
        persist(Region.create("강원도 속초시"));
        persist(Region.create("강원도 평창군"));
        persist(Region.create("강원도 양평군"));
        persist(Region.create("강원도"));


        List<Region> result = repository.findByNames(Lists.newArrayList("강원도", "평창군", "속초시"));

        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0).getName());
    }

    @Test
    void testGetOneWithProgram() {
        Region region = Region.create("강원도 속초시");
        persist(region);
        persist(Region.create("강원도 평창군"));

        Region result = repository.getOneWithPrograms(region.getCode());
        assertThat(result.getName().getName()).isEqualTo("속초시");
    }

    private void persist(Region region) {
        repository.save(region);
    }
}
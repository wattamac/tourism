package org.uhafactory.test.tourism;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.uhafactory.test.DatabaseTestConfig;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Import(DatabaseTestConfig.class)
@Transactional
class RegionRepositoryTest {

    @Autowired
    private RegionRepository regionRepository;

    @Test
    void testParent() {
        Region parent = Region.builder().code("1").name("2").build();

        Region child = Region.builder().code("2").name("child").parent(parent).build();
        regionRepository.save(child);

        Region result = regionRepository.getOne(child.getCode());
        assertThat(result.getCode()).isEqualTo(child.getCode());

        assertThat(result.getParent().getCode()).isEqualTo(parent.getCode());
    }
}
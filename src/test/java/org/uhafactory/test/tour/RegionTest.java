package org.uhafactory.test.tour;

import org.junit.jupiter.api.Test;
import org.uhafactory.test.tour.region.Region;

import static org.assertj.core.api.Assertions.assertThat;

class RegionTest {
    @Test
    void testCreateId() {
        Region region = new Region();

        assertThat(region.createId(1)).isEqualTo("reg1");
    }

    @Test
    void testCreate() {
        String name = "강원도";
        Region region = Region.create(name);
        assertThat(region.getName().getName()).isEqualTo("강원도");

        region = Region.create("전라북도", "전북");
        assertThat(region.getName().getName()).isEqualTo("전라북도");
        assertThat(region.getName().getShortName()).isEqualTo("전북");
    }

}
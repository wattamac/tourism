package org.uhafactory.test.tourism;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegionTest {
    @Test
    void testCreateId() {
        Region region = new Region();

        assertThat(region.createId(1)).isEqualTo("reg1");
    }

}
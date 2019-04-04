package org.uhafactory.tour.util;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.uhafactory.tour.program.RegionNameUtil;

import static org.assertj.core.api.Assertions.assertThat;

class RegionNameUtilTest {

    @Test
    void testGetName() {
        assertThat(RegionNameUtil.extractName("강원도"))
                .containsAll(Lists.newArrayList("강원"));
        assertThat(RegionNameUtil.extractName("강원도 속초시"))
                .containsAll(Lists.newArrayList("강원", "속초"));
        assertThat(RegionNameUtil.extractName("강원도 평창군"))
                .containsAll(Lists.newArrayList("강원", "평창"));
        assertThat(RegionNameUtil.extractName("평창군, 속초~강남"))
                .containsAll(Lists.newArrayList("평창", "속초", "강남"));

        assertThat(RegionNameUtil.extractName(" "))
                .isEmpty();

        assertThat(RegionNameUtil.extractName(null))
                .isEmpty();

        assertThat(RegionNameUtil.extractName("태안국립공원"))
                .containsAll(Lists.newArrayList("태안"));
        assertThat(RegionNameUtil.extractName("서울특별시, 인천광역시"))
                .containsAll(Lists.newArrayList("서울", "인천"));
        assertThat(RegionNameUtil.extractName("도초도"))
                .containsAll(Lists.newArrayList("도초"));

        assertThat(RegionNameUtil.extractName("경상북도 경주"))
                .containsAll(Lists.newArrayList("경북", "경주"));
    }

    @Test
    void testRemovePostfix() {
        assertThat(RegionNameUtil.removePostfix("강원도")).isEqualTo("강원");
        assertThat(RegionNameUtil.removePostfix("평창군")).isEqualTo("평창");
        assertThat(RegionNameUtil.removePostfix(" ")).isEmpty();
        assertThat(RegionNameUtil.removePostfix("서울특별시")).isEqualTo("서울");
        assertThat(RegionNameUtil.removePostfix("경상남도")).isEqualTo("경남");
    }
}
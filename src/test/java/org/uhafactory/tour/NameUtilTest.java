package org.uhafactory.tour;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.uhafactory.tour.util.NameUtil;

import static org.assertj.core.api.Assertions.assertThat;

class NameUtilTest {

    @Test
    void testGetName() {
        assertThat(NameUtil.extractName("강원도"))
                .containsAll(Lists.newArrayList("강원"));
        assertThat(NameUtil.extractName("강원도 속초시"))
                .containsAll(Lists.newArrayList("강원", "속초"));
        assertThat(NameUtil.extractName("강원도 평창군"))
                .containsAll(Lists.newArrayList("강원", "평창"));
        assertThat(NameUtil.extractName("평창군, 속초~강남"))
                .containsAll(Lists.newArrayList("평창", "속초", "강남"));

        assertThat(NameUtil.extractName(" "))
                .isEmpty();

        assertThat(NameUtil.extractName(null))
                .isEmpty();

        assertThat(NameUtil.extractName("태안국립공원"))
                .containsAll(Lists.newArrayList("태안"));
        assertThat(NameUtil.extractName("서울특별시, 인천광역시"))
                .containsAll(Lists.newArrayList("서울", "인천"));
        assertThat(NameUtil.extractName("도초도"))
                .containsAll(Lists.newArrayList("도초"));
    }

    @Test
    void testRemotePostfix() {
        assertThat(NameUtil.removePostfix("강원도")).isEqualTo("강원");
        assertThat(NameUtil.removePostfix("평창군")).isEqualTo("평창");
        assertThat(NameUtil.removePostfix(" ")).isEmpty();
    }

}
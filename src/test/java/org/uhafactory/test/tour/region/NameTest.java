package org.uhafactory.test.tour.region;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NameTest {

    @Test
    void testCreate() {
        Name name = Name.create("강원도");
        assertThat(name.getName()).isEqualTo("강원도");
        assertThat(name.getShortName()).isEqualTo("강원");

        name = Name.create("강원도");
        assertThat(name.getName()).isEqualTo("강원도");
        assertThat(name.getShortName()).isEqualTo("강원");

        name = Name.create("강원도 평창군");
        assertThat(name.getWholeName()).isEqualTo("강원도 평창군");
        assertThat(name.getLevel()).isEqualTo(2);
        assertThat(name.getName()).isEqualTo("평창군");
        assertThat(name.getShortName()).isEqualTo("평창");
    }

    @Test
    void testCreate_invalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Name.create(" "));
    }

    @Test
    void testCreate2() {
        assertThat(Name.create("서울특별시 도봉구").getShortName()).isEqualTo("도봉");
    }
}
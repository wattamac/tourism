package org.uhafactory.tour.program;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KeywordTest {

    @Test
    void testCount() {
        String text = "국립공원의 신비한 자연 생태 강체험과  농촌마을에서의 전통음식, 전통놀이 체험을 통하여 자연과 교감하고 잊혀져가는 우리 문화의 소중함을 되새길 수 있는 프로그램입니다.";
        Keyword keyword = new Keyword("체험");
        assertThat(keyword.matchCount(text)).isEqualTo(2);

        assertThat(keyword.matchCount("체험농장 강체험하고 체험이")).isEqualTo(3);

        assertThat(keyword.matchCount(null)).isEqualTo(0);
    }

    @Test
    void testCreate_invalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Keyword.create(""));
    }
}
package org.uhafactory.tour.configuration;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DataInitializerTest {

    @Test
    void testExtractKeyword() {
        assertThat(DataInitializer.extractKeyword("강원도 고성군"))
                .containsAll(Lists.newArrayList("강원도 고성군", "강원도 고성", "강원 고성", "강원 고성군", "고성군", "고성"));
    }
}
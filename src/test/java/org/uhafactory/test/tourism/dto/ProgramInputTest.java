package org.uhafactory.test.tourism.dto;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProgramInputTest {

    @Test
    void testGetTheme() {
        ProgramInput input = new ProgramInput();
        input.setTheme("a,b,c,");

        assertThat(input.getTheme()).isEqualTo(Lists.newArrayList("a", "b", "c"));

        input.setTheme("a");
        assertThat(input.getTheme()).isEqualTo(Lists.newArrayList("a"));

        input.setTheme(" ");
        assertThat(input.getTheme()).isEmpty();

        input.setTheme(null);
        assertThat(input.getTheme()).isEmpty();
    }
}
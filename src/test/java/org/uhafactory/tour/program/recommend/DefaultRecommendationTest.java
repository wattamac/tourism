package org.uhafactory.tour.program.recommend;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.uhafactory.tour.program.Program;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultRecommendationTest {

    @Test
    void testScore() {
        RecommendationRequest request = new RecommendationRequest();
        request.setKeyword("오대산");

        Program program = Program.builder().theme("!오대산 투어").name("투어").build();

        DefaultRecommendation recommendation = new DefaultRecommendation(request, program);

        assertThat(recommendation.matched()).isTrue();
    }

    @Test
    void testScore_compare() {
        RecommendationRequest request = new RecommendationRequest();
        request.setKeyword("오대산");

        Program program1 = Program.builder().name("오대산 투어").build();
        Program program2 = Program.builder().theme("!오대산 투어").build();
        Program program3 = Program.builder().description("오대산 투어").build();
        Program program4 = Program.builder().name("오대산 오대산").build();

        List<DefaultRecommendation> sorted = Lists.newArrayList(
                new DefaultRecommendation(request, program1),
                new DefaultRecommendation(request, program2),
                new DefaultRecommendation(request, program3),
                new DefaultRecommendation(request, program4)
        ).stream().sorted(Comparator.comparing(Recommendation::score).reversed()).collect(Collectors.toList());


        assertThat(sorted.get(0).getProgram()).isEqualTo(program4);
        assertThat(sorted.get(1).getProgram()).isEqualTo(program2);
        assertThat(sorted.get(2).getProgram()).isEqualTo(program1);
        assertThat(sorted.get(3).getProgram()).isEqualTo(program3);
    }
}
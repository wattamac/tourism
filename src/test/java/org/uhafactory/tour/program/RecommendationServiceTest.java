package org.uhafactory.tour.program;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.uhafactory.tour.program.recommend.Recommendation;
import org.uhafactory.tour.program.recommend.RecommendationCreator;
import org.uhafactory.tour.program.recommend.RecommendationRequest;
import org.uhafactory.tour.program.recommend.RecommendationService;
import org.uhafactory.tour.program.region.Region;
import org.uhafactory.tour.program.region.RegionService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecommendationServiceTest {
    @InjectMocks
    private RecommendationService recommendationService;

    @Mock
    private RegionService regionService;

    @Mock
    private RecommendationCreator recommendationCreator;

    @Test
    void testGetRecommend() {
        RecommendationRequest request = new RecommendationRequest();
        request.setRegion("region");
        request.setKeyword("keyword");

        Program program1 = Program.builder().id("1").build();
        Program program2 = Program.builder().id("2").build();
        Program program3 = Program.builder().id("3").build();
        Program program4 = Program.builder().id("4").build();
        given(regionService.getRegionByName("region")).willReturn(
                aRegion(program1, program2, program3)
        );

        matchedRecommend(request, program1, 3);
        unMatchedRecommend(request, program2);
        matchedRecommend(request, program3, 5);
        matchedRecommend(request, program4, 1);

        Optional<Recommendation> result = recommendationService.getRecommend(request);

        assertThat(result.get().getProgram()).isEqualTo(program3);
    }

    private Region aRegion(Program ... programs) {
        Region region = Region.create("test");
        region.setPrograms(Lists.newArrayList(programs));
        return region;
    }

    Recommendation matchedRecommend(RecommendationRequest request, Program program, float score) {
        Recommendation recommendation = mock(Recommendation.class, withSettings().lenient());
        given(recommendation.matched()).willReturn(true);
        given(recommendation.score()).willReturn(score);
        given(recommendation.getProgram()).willReturn(program);
        lenient().when(recommendationCreator.create(request, program)).thenReturn(recommendation);
        return recommendation;
    }

    Recommendation unMatchedRecommend(RecommendationRequest request, Program program) {
        Recommendation recommendation = mock(Recommendation.class, withSettings().lenient());
        given(recommendation.matched()).willReturn(false);
        lenient().when(recommendationCreator.create(request, program)).thenReturn(recommendation);
        return recommendation;
    }
}
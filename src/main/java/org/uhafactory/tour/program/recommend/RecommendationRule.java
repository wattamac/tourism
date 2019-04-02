package org.uhafactory.tour.program.recommend;

import org.uhafactory.tour.program.Program;

public enum RecommendationRule {
    DEFAULT{
        @Override
        Recommendation create(RecommendationRequest request, Program program) {
            return new DefaultRecommendation(request, program);
        }
    };

    abstract Recommendation create(RecommendationRequest request, Program program);
}

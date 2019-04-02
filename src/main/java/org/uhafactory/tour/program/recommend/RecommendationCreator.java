package org.uhafactory.tour.program.recommend;

import org.springframework.stereotype.Component;
import org.uhafactory.tour.program.Program;

@Component
public class RecommendationCreator {

    public Recommendation create(RecommendationRequest request, Program program) {
        return request.getRule().create(request, program);
    }
}

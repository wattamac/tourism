package org.uhafactory.tour.program.recommend;

import org.uhafactory.tour.program.Keyword;
import org.uhafactory.tour.program.Program;

public class DefaultRecommendation implements Recommendation {
    private final Program program;
    private float score;

    DefaultRecommendation(RecommendationRequest request, Program program) {
        this.program = program;

        this.score = calculate(request, program);
    }

    private int calculate(RecommendationRequest request, Program program) {
        int weight = 0;
        Keyword keyword = Keyword.create(request.getKeyword());
        weight += weight(keyword.matchCount(program.getTheme()), 3f);
        weight += weight(keyword.matchCount(program.getName()), 2f);
        weight += weight(keyword.matchCount(program.getDescription()), 1f);
        weight += weight(keyword.matchCount(program.getDetailDescription()), 0.5f);
        return weight;
    }

    private float weight(int matchCount, float weight) {
        if (matchCount > 0) {
            return matchCount * weight;
        }
        return 0;
    }

    @Override
    public float score() {
        return score;
    }

    @Override
    public boolean matched() {
        return score > 0.0f;
    }

    @Override
    public Program getProgram() {
        return program;
    }
}

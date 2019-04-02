package org.uhafactory.tour.program.recommend;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RecommendationRequest {
    @NotBlank
    private String region;

    @NotBlank
    private String keyword;

    private RecommendationRule rule;

    public RecommendationRule getRule() {
        if(rule == null){
            return RecommendationRule.DEFAULT;
        }
        return rule;
    }
}

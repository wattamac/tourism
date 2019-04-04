package org.uhafactory.tour.program.recommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uhafactory.tour.program.Program;
import org.uhafactory.tour.program.region.Region;
import org.uhafactory.tour.program.region.RegionService;

import java.util.Comparator;
import java.util.Optional;

@Service
public class RecommendationService {
    @Autowired
    private RegionService regionService;

    @Autowired
    private RecommendationCreator recommendationCreator;

    public Optional<Recommendation> getRecommend(RecommendationRequest request) {
        Optional<Region> region = regionService.getRegionByName(request.getRegion());
        return region.map(r -> r.getPrograms().stream()
                .map(p -> toRecommendation(request, p))
                .filter(Recommendation::matched)
                .sorted(Comparator.comparing(Recommendation::score).reversed())
                .findFirst())
                .orElse(Optional.empty());

    }

    private Recommendation toRecommendation(RecommendationRequest request, Program program) {
        return recommendationCreator.create(request, program);
    }
}

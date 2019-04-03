package org.uhafactory.tour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.uhafactory.tour.dto.*;
import org.uhafactory.tour.program.ProgramService;
import org.uhafactory.tour.program.recommend.Recommendation;
import org.uhafactory.tour.program.recommend.RecommendationRequest;
import org.uhafactory.tour.program.recommend.RecommendationService;
import org.uhafactory.tour.program.region.Region;
import org.uhafactory.tour.program.region.RegionService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/tour")
public class TourRestController {
    @Autowired
    private RegionService regionService;

    @Autowired
    private ProgramService programService;

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/regions/{code}")
    public ResponseEntity<RegionDto> getRegionPrograms(@PathVariable("code") String code) {

        Region region = regionService.getRegionByCode(code);
        if(region == null) {
            return notFound();
        }
        return ResponseEntity.ok(DtoMapper.MAPPER.toDto(region));
    }

    @PostMapping("/regions/search/program_themes")
    public ResponseEntity<SimpleRegionDto> getProgramNameAndTheme(@RequestBody Request.RegionNameDto request) {
        Region region = regionService.getRegionByName(request.getRegion());
        if(region == null) {
            return notFound();
        }
        return ResponseEntity.ok(DtoMapper.MAPPER.toSimpleDto(region));
    }

    @PostMapping("/regions/search/regions_count")
    public ResponseEntity<RegionAndCountResult> getRegionAndCount(@RequestBody Request.KeywordDto request) {
        RegionAndCountResult result = regionService.getRegionAndCountInDescriptionKeyword(request.getKeyword());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/regions/search/keyword_count")
    public ResponseEntity<Result.KeywordAndCountDto> getKeywordCount(@RequestBody Request.KeywordDto request) {
        int count = programService.countKeywordAllProgramDescription(request.getKeyword());

        return ResponseEntity.ok(new Result.KeywordAndCountDto(request.getKeyword(), count));
    }

    @PostMapping("/recommend")
    public ResponseEntity<Result.ProgramIdDto> getRecommendProgram(@Valid @RequestBody RecommendationRequest request, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<Recommendation> result = recommendationService.getRecommend(request);
        return result
                .map(r -> new Result.ProgramIdDto(r.getProgram().getId()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    private ResponseEntity notFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}

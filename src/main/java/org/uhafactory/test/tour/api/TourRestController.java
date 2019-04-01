package org.uhafactory.test.tour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uhafactory.test.tour.dto.DescriptionKeywordResult;
import org.uhafactory.test.tour.dto.DtoMapper;
import org.uhafactory.test.tour.dto.RegionDto;
import org.uhafactory.test.tour.dto.SimpleRegionDto;
import org.uhafactory.test.tour.region.Region;
import org.uhafactory.test.tour.region.RegionService;

import java.util.Map;

@RestController
@RequestMapping("/tour")
public class TourRestController {
    @Autowired
    private RegionService regionService;

    @GetMapping("/regions/{code}")
    public ResponseEntity<RegionDto> getRegionPrograms(@PathVariable("code") String code) {

        Region region = regionService.getRegionByCode(code);
        if(region == null) {
            return notFound();
        }
        return ResponseEntity.ok(DtoMapper.MAPPER.toDto(region));
    }

    @PostMapping("/regions/search/program_themes")
    public ResponseEntity<SimpleRegionDto> getProgramNameAndTheme(@RequestBody Map<String, String> request) {
        Region region = regionService.getRegionByName(request.get("region"));
        if(region == null) {
            return notFound();
        }
        return ResponseEntity.ok(DtoMapper.MAPPER.toSimpleDto(region));
    }

    @PostMapping("/regions/search/keyword")
    public ResponseEntity<DescriptionKeywordResult> getKeywordCount(@RequestBody Map<String, String> request) {
        DescriptionKeywordResult result = regionService.getDescriptionKeywordCount(request.get("keyword"));
        return ResponseEntity.ok(result);
    }

    private ResponseEntity notFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

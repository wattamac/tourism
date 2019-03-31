package org.uhafactory.test.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.uhafactory.test.api.dto.RegionDto;
import org.uhafactory.test.api.dto.RegionMapper;
import org.uhafactory.test.tourism.region.Region;
import org.uhafactory.test.tourism.region.RegionService;

@RestController("/tour")
public class TourRestController {
    @Autowired
    private RegionService regionService;

    @GetMapping("/regions/{code}")
    public ResponseEntity<RegionDto> getRegionPrograms(@PathVariable("code") String code) {

        Region region = regionService.getRegionByCode(code);
        return ResponseEntity.ok(RegionMapper.MAPPER.toRegionDto(region));
    }
}

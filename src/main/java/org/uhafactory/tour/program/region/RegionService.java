package org.uhafactory.tour.program.region;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.uhafactory.tour.api.dto.RegionAndCountResult;
import org.uhafactory.tour.program.RegionNameUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getRegions(String wholeName) {
        List<String> names = RegionNameUtil.extractName(wholeName);
        if (CollectionUtils.isEmpty(names)) {
            return Collections.emptyList();
        }
        return regionRepository.findByNames(names);
    }

    public Optional<Region> getRegionByCode(String code) {
        return regionRepository.findByCodeWithPrograms(code);
    }

    public Optional<Region> getRegionByName(String name) {
        return regionRepository.findByNameWithPrograms(name);
    }

    public RegionAndCountResult getRegionAndCountInDescriptionKeyword(String keyword) {
        if(StringUtils.isBlank(keyword)) {
            return RegionAndCountResult.empty();
        }
        List<RegionAndCountResult.RegionAndProgramCount> result = regionRepository.findByProgramInContainsDescriptionKeyword(keyword);
        return RegionAndCountResult.create(keyword, result);
    }


}

package org.uhafactory.test.tour.region;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.uhafactory.test.tour.dto.DescriptionKeywordResult;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getRegions(String wholeName) {
        List<String> names = NameUtil.extractName(wholeName);
        if (CollectionUtils.isEmpty(names)) {
            return Collections.emptyList();
        }
        return regionRepository.findByNames(names);
    }

    public Region getRegionByCode(String code) {
        return regionRepository.findByCodeWithPrograms(code);
    }

    public Region getRegionByName(String name) {
        return regionRepository.findByNameWithPrograms(name);
    }

    public DescriptionKeywordResult getDescriptionKeywordCount(String keyword) {
        if(StringUtils.isBlank(keyword)) {
            return DescriptionKeywordResult.empty();
        }
        List<DescriptionKeywordResult.RegionAndProgramCount> result = regionRepository.findByProgramInContainsDescriptionKeyword(keyword);
        return DescriptionKeywordResult.create(keyword, result);
    }
}

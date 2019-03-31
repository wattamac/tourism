package org.uhafactory.test.tourism.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
        return regionRepository.getOneWithPrograms(code);
    }
}

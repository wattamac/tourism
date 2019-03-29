package org.uhafactory.test.tourism.dto;

import org.springframework.stereotype.Service;
import org.uhafactory.test.tourism.Region;
import org.uhafactory.test.tourism.RegionRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class RegionService {

    private RegionRepository regionRepository;

    public Region getRegion(String name) {
        return null;
    }
}

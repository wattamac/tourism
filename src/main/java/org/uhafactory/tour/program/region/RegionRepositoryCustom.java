package org.uhafactory.tour.program.region;

import org.uhafactory.tour.dto.RegionAndCountResult;

import java.util.Collection;
import java.util.List;

public interface RegionRepositoryCustom {
    Region findByNameWithPrograms(String name);

    List<Region> findByNames(Collection<String> names);

    Region findByCodeWithPrograms(String code);

    List<RegionAndCountResult.RegionAndProgramCount> findByProgramInContainsDescriptionKeyword(String keyword);
}

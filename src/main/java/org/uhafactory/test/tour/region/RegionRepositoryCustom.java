package org.uhafactory.test.tour.region;

import org.uhafactory.test.tour.dto.DescriptionKeywordResult;

import java.util.Collection;
import java.util.List;

public interface RegionRepositoryCustom {
    Region findByNameWithPrograms(String name);

    List<Region> findByNames(Collection<String> names);

    Region findByCodeWithPrograms(String code);

    List<DescriptionKeywordResult.RegionAndProgramCount> findByProgramInContainsDescriptionKeyword(String keyword);
}

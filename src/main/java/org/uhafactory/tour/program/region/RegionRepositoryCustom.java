package org.uhafactory.tour.program.region;

import org.uhafactory.tour.api.dto.RegionAndCountResult;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RegionRepositoryCustom {
    Optional<Region> findByNameWithPrograms(String name);

    List<Region> findByNames(Collection<String> names);

    Optional<Region> findByCodeWithPrograms(String code);

    List<RegionAndCountResult.RegionAndProgramCount> findByProgramInContainsDescriptionKeyword(String keyword);
}

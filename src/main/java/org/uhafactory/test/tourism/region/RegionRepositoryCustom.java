package org.uhafactory.test.tourism.region;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RegionRepositoryCustom {
    Optional<Region> findByName(String name);

    List<Region> findByNames(Collection<String> names);

    Region getOneWithPrograms(String code);
}

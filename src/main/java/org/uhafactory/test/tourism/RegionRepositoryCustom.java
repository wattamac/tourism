package org.uhafactory.test.tourism;

import java.util.Optional;

public interface RegionRepositoryCustom {
    Optional<Region> findByName(String name);
}

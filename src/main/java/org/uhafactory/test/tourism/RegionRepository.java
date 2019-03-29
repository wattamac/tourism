package org.uhafactory.test.tourism;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, String>, RegionRepositoryCustom {
}

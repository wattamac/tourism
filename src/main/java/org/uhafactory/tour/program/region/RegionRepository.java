package org.uhafactory.tour.program.region;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, String>, RegionRepositoryCustom {
}

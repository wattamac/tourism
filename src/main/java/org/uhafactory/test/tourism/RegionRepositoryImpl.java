package org.uhafactory.test.tourism;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

public class RegionRepositoryImpl extends QuerydslRepositorySupport implements RegionRepositoryCustom {
    public RegionRepositoryImpl() {
        super(Region.class);
    }


    @Override
    public Optional<Region> findByName(String name) {
        return Optional.empty();
    }
}

package org.uhafactory.test.tourism.region;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.uhafactory.test.tourism.program.QProgram;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class RegionRepositoryImpl extends QuerydslRepositorySupport implements RegionRepositoryCustom {
    public RegionRepositoryImpl() {
        super(Region.class);
    }


    @Override
    public Optional<Region> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Region> findByNames(Collection<String> names) {
        return from(QRegion.region)
                .where(QRegion.region.name.shortName.in(names))
                .fetch();
    }

    @Override
    public Region getOneWithPrograms(String code) {
        return from(QRegion.region)
                .leftJoin(QRegion.region.programs, QProgram.program).fetchJoin()
                .where(QRegion.region.code.eq(code))
                .fetchOne();
    }
}

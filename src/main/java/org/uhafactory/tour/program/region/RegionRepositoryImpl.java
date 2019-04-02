package org.uhafactory.tour.program.region;

import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.uhafactory.tour.dto.RegionAndCountResult;
import org.uhafactory.tour.program.QProgram;

import java.util.Collection;
import java.util.List;

public class RegionRepositoryImpl extends QuerydslRepositorySupport implements RegionRepositoryCustom {
    public RegionRepositoryImpl() {
        super(Region.class);
    }

    @Override
    public Region findByNameWithPrograms(String name) {
        return from(QRegion.region)
                .leftJoin(QRegion.region.programs, QProgram.program).fetchJoin()
                .where(QRegion.region.name.name.eq(name))
                .fetchOne();
    }

    @Override
    public List<Region> findByNames(Collection<String> names) {
        return from(QRegion.region)
                .where(QRegion.region.name.shortName.in(names))
                .fetch();
    }

    @Override
    public Region findByCodeWithPrograms(String code) {
        return from(QRegion.region)
                .leftJoin(QRegion.region.programs, QProgram.program).fetchJoin()
                .where(QRegion.region.code.eq(code))
                .fetchOne();
    }

    @Override
    public List<RegionAndCountResult.RegionAndProgramCount> findByProgramInContainsDescriptionKeyword(String keyword) {
        return from(QRegion.region)
                .join(QRegion.region.programs, QProgram.program)
                .where(QProgram.program.description.contains(keyword))
                .groupBy(QRegion.region.code)
                .select(Projections.constructor(RegionAndCountResult.RegionAndProgramCount.class,
                        QRegion.region.name, QRegion.region.code.count()))
                .orderBy(QRegion.region.code.count().desc())
                .fetch();
    }
}

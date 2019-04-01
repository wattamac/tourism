package org.uhafactory.test.tour.program;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.uhafactory.test.tour.region.QRegion;

public class ProgramRepositoryImpl extends QuerydslRepositorySupport implements ProgramRepositoryCustom {
    public ProgramRepositoryImpl() {
        super(Program.class);
    }

    private QProgram program = QProgram.program;
    private QRegion region = QRegion.region;
//
//    @Override
//    public List<Program> findByRegionCode(String code) {
//        return from(program)
//                .innerJoin(program.regions, region)
//                .where(region.code.eq(code))
//                .fetch();
//    }
}

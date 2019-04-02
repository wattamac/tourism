package org.uhafactory.tour.program;

import com.mysema.commons.lang.CloseableIterator;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ProgramRepositoryImpl extends QuerydslRepositorySupport implements ProgramRepositoryCustom {
    public ProgramRepositoryImpl() {
        super(Program.class);
    }

    private QProgram program = QProgram.program;

    public CloseableIterator<Program> getAll() {
        return from(program).iterate();
    }
}

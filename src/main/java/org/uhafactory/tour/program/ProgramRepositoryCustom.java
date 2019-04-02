package org.uhafactory.tour.program;

import com.mysema.commons.lang.CloseableIterator;

public interface ProgramRepositoryCustom {
    CloseableIterator<Program> getAll();
}

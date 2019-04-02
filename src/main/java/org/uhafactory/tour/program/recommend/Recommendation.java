package org.uhafactory.tour.program.recommend;

import org.uhafactory.tour.program.Program;

public interface Recommendation {
    float score();

    boolean matched();

    Program getProgram();
}

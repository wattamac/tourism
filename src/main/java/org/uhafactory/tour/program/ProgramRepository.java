package org.uhafactory.tour.program;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, String>, ProgramRepositoryCustom {

}

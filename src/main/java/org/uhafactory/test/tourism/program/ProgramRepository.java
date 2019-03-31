package org.uhafactory.test.tourism.program;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, String>, ProgramRepositoryCustom {

}

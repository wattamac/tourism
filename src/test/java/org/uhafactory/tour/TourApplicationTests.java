package org.uhafactory.tour;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.uhafactory.tour.program.ProgramRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TourApplicationTests {
	@Autowired
	private ProgramRepository programRepository;

	@Test
	void contextLoads() {
		assertThat(programRepository).isNotNull();
	}

}

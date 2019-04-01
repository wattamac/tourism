package org.uhafactory.test.tour;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.uhafactory.test.tour.program.ProgramRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TourApplicationTests {
	@Autowired
	private ProgramRepository programRepository;

	@Test
	public void contextLoads() {
		assertThat(programRepository).isNotNull();
	}

}

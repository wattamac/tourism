package org.uhafactory.tour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.uhafactory.tour.configuration.DatabaseConfiguration;

@SpringBootApplication
@Import(DatabaseConfiguration.class)
public class TourApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourApplication.class, args);
	}

}

package org.uhafactory.tour.api;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.uhafactory.tour.TourApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TourApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
    @LocalServerPort
    private int port;

    protected TestRestTemplate testRestTemplate = new TestRestTemplate();

    protected String createURL(String uri) {
        return "http://localhost:" + port + uri;
    }
}

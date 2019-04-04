package org.uhafactory.tour.api;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.uhafactory.tour.TourApplication;
import org.uhafactory.tour.api.dto.ApiResponse;
import org.uhafactory.tour.api.dto.JwtAuthenticationResponse;
import org.uhafactory.tour.api.dto.LoginRequest;
import org.uhafactory.tour.api.dto.SignUpRequest;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TourApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
    @LocalServerPort
    private int port;

    protected TestRestTemplate testRestTemplate = new TestRestTemplate();

    protected String createURL(String uri) {
        return "http://localhost:" + port + uri;
    }

    private static String loginId = "ABCD";
    private static String password = "11231OK";

    public boolean signUp() {
        SignUpRequest request = new SignUpRequest();
        request.setLoginId(loginId);
        request.setName("name");
        request.setPassword(password);
        ResponseEntity<ApiResponse> response = testRestTemplate.postForEntity(
                createURL("/tour/auth/signup"), request, ApiResponse.class);
        ApiResponse result = response.getBody();
        return result.isSuccess();
    }

    public String getToken() {
        ResponseEntity<JwtAuthenticationResponse> response = testRestTemplate.postForEntity(
                createURL("/tour/auth/signin"), new LoginRequest(loginId, password), JwtAuthenticationResponse.class);

        return response.getBody().getAccessToken();
    }
}

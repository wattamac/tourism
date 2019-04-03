package org.uhafactory.tour.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.uhafactory.tour.security.JwtTokenProvider;
import org.uhafactory.tour.security.User;
import org.uhafactory.tour.security.UserRepository;
import org.uhafactory.tour.security.ApiResponse;
import org.uhafactory.tour.security.JwtAuthenticationResponse;
import org.uhafactory.tour.security.LoginRequest;
import org.uhafactory.tour.security.SignUpRequest;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/tour/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLoginId(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.findById(signUpRequest.getLoginId()).isPresent()) {
            return new ResponseEntity(ApiResponse.fail("Login is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .loginId(signUpRequest.getLoginId())
                .name(signUpRequest.getName())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{loginId}")
                .buildAndExpand(result.getLoginId()).toUri();

        return ResponseEntity.created(location).body(ApiResponse.success());
    }
}

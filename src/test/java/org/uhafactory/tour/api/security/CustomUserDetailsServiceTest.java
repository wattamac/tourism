package org.uhafactory.tour.api.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.uhafactory.tour.user.User;
import org.uhafactory.tour.user.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private UserRepository userRepository;

    @Test
    void testLoginId() {
        String loginId = "11";

        given(userRepository.findById(loginId)).willReturn(
                Optional.of(new User(loginId, "name", "password")));

        UserDetails user = customUserDetailsService.loadUserByUsername(loginId);
        assertThat(user.getUsername()).isEqualTo(loginId);
        assertThat(user.getPassword()).isEqualTo("password");
    }

    @Test
    void testLoginId_not_found() {
        String loginId = "11";

        given(userRepository.findById(loginId)).willReturn(Optional.empty());

        Assertions.assertThrows(UsernameNotFoundException.class, () ->
                customUserDetailsService.loadUserByUsername(loginId));
    }
}
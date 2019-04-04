package org.uhafactory.tour.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uhafactory.tour.user.User;
import org.uhafactory.tour.user.UserRepository;

@Transactional(readOnly = true)
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByLoginId(username);
    }

    public UserDetails loadUserByLoginId(String loginId) {
        User user = userRepository.findById(loginId)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with loginId : " + loginId)
                );

        return UserPrincipal.create(user);
    }
}
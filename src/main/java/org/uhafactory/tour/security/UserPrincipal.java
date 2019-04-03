package org.uhafactory.tour.security;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@Getter
public class UserPrincipal implements UserDetails {
    private String loginId;

    private String password;

    public static UserDetails create(User user) {
        return new UserPrincipal(user.getLoginId(), user.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ImmutableList.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
//    private Long id;
//
//    private String loginId;
//
//    private String name;
//
//    @JsonIgnore
//    private String email;
//
//    @JsonIgnore
//    private String password;
//
//    private Collection<? extends GrantedAuthority> authorities;

//    public UserPrincipal(Long id, String name, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
//        this.id = id;
//        this.name = name;
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.authorities = authorities;
//    }
//
//    public static UserPrincipal create(User user) {
//        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
//                new SimpleGrantedAuthority(role.getName().name())
//        ).collect(Collectors.toList());
//
//        return new UserPrincipal(
//                user.getId(),
//                user.getName(),
//                user.getUsername(),
//                user.getEmail(),
//                user.getPassword(),
//                authorities
//        );
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}

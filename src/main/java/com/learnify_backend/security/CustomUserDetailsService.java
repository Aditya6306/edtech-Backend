package com.learnify_backend.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import com.learnify_backend.repository.UserRepository;
import com.learnify_backend.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String usernameOrEmail) throws RuntimeException {
        User user = userRepository.findByEmail(usernameOrEmail)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + usernameOrEmail));

        Set<GrantedAuthority> authorities = Set.of(
                new SimpleGrantedAuthority(user.getRole().getRoleName()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities);
    }
}

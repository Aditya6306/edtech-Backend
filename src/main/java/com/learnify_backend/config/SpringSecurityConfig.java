package com.learnify_backend.config;

import java.security.Security;

import java.util.List;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lombok.AllArgsConstructor;
import com.learnify_backend.security.Jwt;
import com.learnify_backend.security.CustomUserDetailsService;
import com.learnify_backend.security.JwtAuthenticationFilter;



@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService;

    private Jwt jwtAuthenticationEntryPoint;

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(
            List.of("http://localhost:3000")
        );

        configuration.setAllowedMethods(
            List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
        );

        configuration.setAllowedHeaders(
            List.of("*")
        );

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .cors(Customizer.withDefaults())
            .csrf((csrf)->csrf.disable())
            .authorizeHttpRequests((authz) -> {
                // authz.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
                // authz.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
                // authz.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
                // authz.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("USER", "ADMIN");
                // authz.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("USER", "ADMIN");

                // authz.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
                authz.requestMatchers("/api/auth/**").permitAll();
                authz.requestMatchers("/api/course/showAllCategories").permitAll();
                authz.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();

                authz.anyRequest().authenticated();
            }).httpBasic(Customizer.withDefaults());

            http.exceptionHandling((exception) -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint));

            http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails user = User.builder()
    //         .username("user")
    //         .password(passwordEncoder().encode("1234"))
    //         .roles("USER")
    //         .build();

    //     UserDetails admin = User.builder()
    //         .username("admin")
    //         .password(passwordEncoder().encode("admin"))
    //         .roles("ADMIN")
    //         .build();

    //     return new InMemoryUserDetailsManager(user, admin);
    // }
}

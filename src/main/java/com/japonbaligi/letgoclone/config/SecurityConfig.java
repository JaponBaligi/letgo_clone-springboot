package com.japonbaligi.letgoclone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authz -> authz
                                                .requestMatchers("/", "/search", "/ilan-ver", "/login", "/register",
                                                                "/logout", "/auth/status",
                                                                "/product/**", "/css/**", "/js/**",
                                                                "/images/**", "/h2-console/**",
                                                                "/checkout", "/checkout/**")
                                                .permitAll()
                                                .anyRequest().permitAll())
                                .csrf(csrf -> csrf
                                                .ignoringRequestMatchers("/h2-console/**", "/login", "/register",
                                                                "/logout",
                                                                "/cart/**", "/checkout/**"))
                                .headers(headers -> headers
                                                .frameOptions(frameOptions -> frameOptions.sameOrigin()))
                                .sessionManagement(session -> session
                                                .maximumSessions(1)
                                                .maxSessionsPreventsLogin(false))
                                .logout(logout -> logout
                                                .disable());

                return http.build();
        }
}

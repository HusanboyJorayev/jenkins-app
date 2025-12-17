package org.example.jenkins.config;

import jakarta.servlet.Filter;
//import org.example.jenkins.filter.JwtAuthenticationFilter;
//import org.example.jenkins.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@Configuration
//public class SecurityConfig {
//
//    private final JwtFilter jwtFilter;
//
//    public SecurityConfig(JwtFilter jwtFilter) {
//        this.jwtFilter = jwtFilter;
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/jenkins/v3/api-docs/**",
//                                "/jenkins/swagger-ui/**",
//                                "/jenkins/swagger-ui/**/webjars/**",
//                                "/jenkins/webjars/**"
//                        ).permitAll()
//                        .anyRequest().authenticated()
//                );
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Filter tartibi muhim
//        return http.build();
//    }
//}

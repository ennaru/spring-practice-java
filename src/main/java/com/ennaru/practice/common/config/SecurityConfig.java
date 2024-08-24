package com.ennaru.practice.common.config;

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
        return http
                .authorizeHttpRequests(authorize ->
                        authorize
                                //.requestMatchers("/security/**").hasRole("AUDITOR")
                                .requestMatchers("/jpa/**").permitAll()
                                .requestMatchers("/transactional/**").permitAll()
                                .anyRequest().authenticated()
                )
                .csrf(csrf ->
                        csrf.disable())
                .build();
    }

}

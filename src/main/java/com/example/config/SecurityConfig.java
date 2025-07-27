package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // ✅ disable CSRF for POST JSON
            .authorizeHttpRequests(auth -> auth
            		 .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                     .requestMatchers(HttpMethod.POST, "/api/**").authenticated()
                     .requestMatchers(HttpMethod.PUT, "/api/**").authenticated()
                     .requestMatchers(HttpMethod.DELETE, "/api/**").authenticated()
                     .anyRequest().permitAll() // allow others like GET without auth
            )
            .httpBasic(); // enable Basic Auth
        return http.build();
    }
}

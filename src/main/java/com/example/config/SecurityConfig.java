package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.security.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private final JwtFilter jwtFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
								// Category endpoints
								.requestMatchers(HttpMethod.GET, "/api/category/**").authenticated() // GET accessible
																										// to all

								.requestMatchers("/api/category/**").hasRole("ADMIN")

								// Orders endpoints
								.requestMatchers(HttpMethod.GET, "/api/orders/**").authenticated() // GET only for ADMIN

								.requestMatchers("/api/orders/**").hasRole("USER") // Other methods only for USER

								// Products endpoints
								.requestMatchers(HttpMethod.GET, "/api/products/**").authenticated() // GET for USER &
																										// ADMIN
								.requestMatchers("/api/products/**").hasRole("ADMIN") // other methods for ADMIN

								.anyRequest().authenticated())
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

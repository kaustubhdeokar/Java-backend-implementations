package com.example.dao_auth.config;

import com.example.dao_auth.auth.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtAuthProvider jwtAuthProvider;
    private final CustomDaoAuthProvider customDaoAuthProvider;
    private final JwtUtil jwtUtil;

    public SecurityConfiguration(CustomUserDetailsService customUserDetailsService, JwtAuthProvider jwtAuthProvider, CustomDaoAuthProvider customDaoAuthProvider, JwtUtil jwtUtil) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtAuthProvider = jwtAuthProvider;
        this.customDaoAuthProvider = customDaoAuthProvider;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //noinspection removal
        http
                .csrf().disable()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(customDaoAuthProvider)
                .authenticationProvider(jwtAuthProvider)
                .userDetailsService(customUserDetailsService)
                .addFilterBefore(new JwtAuthFilter(jwtAuthProvider), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> {
                            auth.requestMatchers("/api/auth/**").permitAll();
                            auth.anyRequest().authenticated();
                        }
                ).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Arrays.asList(jwtAuthProvider,
                customDaoAuthProvider));
    }

}

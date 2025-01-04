package com.example.dao_auth.config;

import com.example.dao_auth.auth.CustomAuthFilter;
import com.example.dao_auth.auth.CustomDaoAuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final CustomDaoAuthProvider customDaoAuthProvider;

    public SecurityConfiguration(CustomDaoAuthProvider customDaoAuthProvider) {
        this.customDaoAuthProvider = customDaoAuthProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomDaoAuthProvider customDaoAuthProvider) throws Exception {
        //noinspection removal

        CustomAuthFilter authFilter = new CustomAuthFilter(authenticationManager());
        authFilter.setAuthenticationManager(authenticationManager());
        http
                .csrf().disable()
                .authenticationProvider(customDaoAuthProvider)
                .authorizeHttpRequests(auth -> {
                            auth.requestMatchers("/api/auth/**").permitAll();
                            auth.anyRequest().authenticated();
                        }
                ).httpBasic(Customizer.withDefaults());
        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Arrays.asList(customDaoAuthProvider));
    }

}

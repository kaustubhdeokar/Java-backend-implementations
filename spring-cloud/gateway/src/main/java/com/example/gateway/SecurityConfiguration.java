package com.example.gateway;

import com.example.gateway.service.CustomDaoAuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomDaoAuthProvider customDaoAuthProvider) throws Exception {
        //noinspection removal
        http
                .authenticationProvider(customDaoAuthProvider)
                .csrf().disable()
                .authorizeHttpRequests((auth) -> {
                            auth.requestMatchers("api/public/**").permitAll();
                            auth.anyRequest().authenticated();
                        }
                ).httpBasic(Customizer.withDefaults());
        return http.build();
    }

}

package com.example.dao_auth.auth;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomDaoAuthProvider extends DaoAuthenticationProvider {

    public CustomDaoAuthProvider(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        setUserDetailsService(customUserDetailsService);
        setPasswordEncoder(passwordEncoder);
    }
}

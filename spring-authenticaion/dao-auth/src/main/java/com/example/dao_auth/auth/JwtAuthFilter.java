package com.example.dao_auth.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtAuthProvider jwtAuthProvider;
    public JwtAuthFilter(JwtAuthProvider jwtAuthProvider) {
        this.jwtAuthProvider = jwtAuthProvider;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if ("/api/auth/login".equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = getTokenFromRequest(request);
        try {
            JwtAuthToken token = new JwtAuthToken(jwt);
            Authentication authentication = jwtAuthProvider.authenticate(token);
            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            System.out.println("jwt auth failed");
        }
        filterChain.doFilter(request, response);
    }

    private boolean isRegisterOrLoginRequest(HttpServletRequest request) {
        return request.getRequestURI().contains("register") || request.getRequestURI().contains("login");
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        final String bearer = "Bearer ";
        String authHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authHeader) && authHeader.startsWith(bearer)) {
            return authHeader.substring(bearer.length());
        }
        return null;
    }

}

package com.example.BE.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtAuthenticationProvider;

    public JwtAuthenticationFilter(JwtTokenProvider provider) {
        this.jwtAuthenticationProvider = provider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtAuthenticationProvider.resolveToken(request);

        if (token != null) {
            try {
                if (jwtAuthenticationProvider.validateToken(token)) {
                    Authentication authentication = jwtAuthenticationProvider.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("올바르지 않은 데이터입니다.");
                    return;
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("올바르지 않은 데이터입니다.");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
package ecc.sogonsogon.BE.security;

import ecc.sogonsogon.BE.entity.User;
import ecc.sogonsogon.BE.repository.UserRepository;
import ecc.sogonsogon.BE.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtUtil;

    @Autowired
    private UserRepository userRepository;

    // 요청이 있을 때마다 호출
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getTokenFromRequest(request);
        if (token != null && jwtUtil.isTokenExpired(token)) {
            // 토큰이 만료되었으면 401 Unauthorized 응답
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (token != null) {
            String email = jwtUtil.extractEmail(token);
            User user = userRepository.findByEmail(email).orElse(null);

            if (user != null && jwtUtil.validateToken(token, user)) {
                // 인증이 성공하면, 인증된 사용자 정보를 설정
                request.setAttribute("user", user);
            }
        }

        filterChain.doFilter(request, response);
    }

    // 요청 헤더에서 JWT 토큰 추출
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);  // "Bearer "를 제거하고 토큰만 반환
        }
        return null;
    }
}
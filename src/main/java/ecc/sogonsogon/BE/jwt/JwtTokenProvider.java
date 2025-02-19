package ecc.sogonsogon.BE.jwt;

import ecc.sogonsogon.BE.entity.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;  // 비밀 키

    @Value("${jwt.expiration}")
    private long expirationTime;  // 만료 시간 (밀리초 단위)

    // JWT 생성
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())  // 이메일을 Subject로 설정
                .claim("nickname", user.getNickname())  // 닉네임을 클레임으로 추가
                .setIssuedAt(new Date())  // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))  // 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    // JWT 유효성 검증
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // JWT 만료일 추출
    private Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    // JWT 검증
    public boolean validateToken(String token, User user) {
        String email = extractEmail(token);
        return (email.equals(user.getEmail()) && !isTokenExpired(token));
    }
}
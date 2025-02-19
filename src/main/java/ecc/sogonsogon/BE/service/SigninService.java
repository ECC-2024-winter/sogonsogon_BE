package ecc.sogonsogon.BE.service;

import ecc.sogonsogon.BE.entity.User;
import ecc.sogonsogon.BE.exception.GlobalException;
import ecc.sogonsogon.BE.jwt.JwtTokenProvider;
import ecc.sogonsogon.BE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SigninService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // 로그인 메서드
    public String signin(String email, String password) {
        // 이메일로 사용자 조회
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new GlobalException.UserNotFoundException("존재하지 않는 사용자입니다."));

        // 비밀번호 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 토큰 생성
        return jwtTokenProvider.generateToken(user.getUserId(), user.getRole().name());
    }
}
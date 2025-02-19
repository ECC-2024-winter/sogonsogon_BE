package ecc.sogonsogon.BE.service;

import ecc.sogonsogon.BE.entity.User;
import ecc.sogonsogon.BE.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 사용자 등록
    public User registerUser(User user) {
        // 비밀번호 암호화 후 저장
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // 사용자 조회
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
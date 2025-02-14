package com.example.BE.service;

import com.example.BE.dto.UserDto;
import com.example.BE.entity.Role;
import com.example.BE.entity.User;
import com.example.BE.exception.DuplicateIdException;
import com.example.BE.exception.MissingUserInfoException;
import com.example.BE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public SignupService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User create(UserDto dto) {
        // UserDto를 User 엔티티로 변환
        User user = dto.toEntity();

        // 필수 정보 체크
        if (user.getNickname() == null || user.getEmail() == null || user.getPw() == null) {
            throw new MissingUserInfoException("올바르지 않은 사용자입니다.");
        }

        // 이메일로 중복된 유저 체크
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateIdException("이미 사용중인 이메일입니다.");
        }

        // 비밀번호 암호화
        user.setPw(encoder.encode(user.getPw()));

        // 기본 권한 설정 (ROLE_USER)
        if (user.getRole() == null) {
            user.setRole(Role.ROLE_USER);
        }
        return userRepository.save(user);
    }
}
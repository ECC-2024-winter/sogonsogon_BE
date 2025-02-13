package com.example.BE.service;

import com.example.BE.dto.NicknameChangeDto;
import com.example.BE.dto.PwChangeDto;
import com.example.BE.entity.User;
import com.example.BE.exception.UserNotFoundException;
import com.example.BE.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 비밀번호 확인
    public boolean validatePw(String userId, String pw) {
        // userId로 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 사용자입니다."));

        // 입력된 비밀번호와 DB에 저장된 비밀번호 비교
        return passwordEncoder.matches(pw, user.getPw());
    }

    // 닉네임 확인
    public boolean validateName(String userId, String name) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 사용사입니다."));

        // 입력한 닉네임과 실제 닉네임 비교
        return user.getNickname().equals(name);
    }

    // 비밀번호 변경 메서드
    public void changePassword(String userId, PwChangeDto pwChangeDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 사용자입니다."));

        // 기존 비밀번호 확인
        if (!passwordEncoder.matches(pwChangeDto.getOldPassword(), user.getPw())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        // 새로운 비밀번호 설정
        user.setPw(passwordEncoder.encode(pwChangeDto.getNewPassword()));
        userRepository.save(user);
    }

    // 닉네임 변경 메서드
    public void changeNickname(String userId, NicknameChangeDto nicknameChangeDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("해당 아이디 " + userId + "는 존재하지 않습니다."));

        // 새로운 닉네임 설정
        user.setNickname(nicknameChangeDto.getNewNickname());
        userRepository.save(user);
    }
}
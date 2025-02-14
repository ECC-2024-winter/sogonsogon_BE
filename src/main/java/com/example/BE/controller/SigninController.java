package com.example.BE.controller;

import com.example.BE.dto.SigninRequestDto;
import com.example.BE.entity.User;
import com.example.BE.exception.UserNotFoundException;
import com.example.BE.jwt.JwtTokenProvider;
import com.example.BE.repository.UserRepository;
import com.example.BE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins ="*", methods={RequestMethod.POST})
public class SigninController {
    @Autowired
    private UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Transactional
    @PostMapping("user/signin")
    public ResponseEntity<String> signIn(@RequestBody SigninRequestDto dto){
        // 비밀번호 일치 확인
        boolean result = userService.validatePw(dto.getEmail(), dto.getPw());

        // Response: 401 에러
        if (!result) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("올바르지 않은 데이터입니다.");
        }
        // Token
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        String role = user.getRole().name();
        String token = jwtTokenProvider.createToken(dto.getEmail(), role);

        // Response: 200 성공
        return ResponseEntity.status(HttpStatus.OK).body("jwtAccessToken: "+token);
    }

    // Response: 404 에러
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
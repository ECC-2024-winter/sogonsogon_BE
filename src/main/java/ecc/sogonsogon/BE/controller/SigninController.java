package ecc.sogonsogon.BE.controller;

import ecc.sogonsogon.BE.dto.SigninRequestDto;
import ecc.sogonsogon.BE.dto.SigninResponseDto;
import ecc.sogonsogon.BE.entity.User;
import ecc.sogonsogon.BE.exception.GlobalException;
import ecc.sogonsogon.BE.jwt.JwtTokenProvider;
import ecc.sogonsogon.BE.repository.UserRepository;
import ecc.sogonsogon.BE.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
@RequestMapping("/user")
public class SigninController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public SigninController(UserService userService, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    @PostMapping("/signin")
    public ResponseEntity<SigninResponseDto> signIn(@Valid @RequestBody SigninRequestDto dto) {
        // 사용자 조회
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new GlobalException.UserNotFoundException("존재하지 않는 사용자입니다."));

        // 비밀번호 검증
        if (!userService.validatePw(dto.getEmail(), dto.getPw())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new SigninResponseDto(null, "이메일 또는 비밀번호가 올바르지 않습니다."));
        }

        // JWT 토큰 생성
        String token = jwtTokenProvider.generateToken(dto.getEmail(), user.getRole().name());

        // 성공 응답 (JSON 형식)
        return ResponseEntity.ok(new SigninResponseDto(token, "로그인 성공"));
    }
}
package ecc.sogonsogon.BE.controller;

import ecc.sogonsogon.BE.entity.User;
import ecc.sogonsogon.BE.security.JwtUtil;
import ecc.sogonsogon.BE.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        authService.signup(request.getEmail(), request.getPassword(), request.getNickname());
        return "회원가입 성공!";
    }

    @PostMapping("/signin")
    public String signin(@RequestBody SigninRequest request) {
        User user = authService.authenticate(request.getEmail(), request.getPassword());
        return jwtUtil.generateToken(user.getEmail());
    }

    @PostMapping("/signout")
    public String signout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            // 클라이언트에서 JWT 삭제 요청 (프론트엔드에서 처리)
            return "로그아웃 성공!";
        }
        return "잘못된 요청입니다.";
    }
}

// DTO 클래스
class SignupRequest {
    private String email;
    private String password;
    private String nickname;

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getNickname() { return nickname; }
}

class SigninRequest {
    private String email;
    private String password;

    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
package ecc.sogonsogon.BE.controller;

import ecc.sogonsogon.BE.dto.PwChangeDto;
import ecc.sogonsogon.BE.dto.NicknameChangeDto;
import ecc.sogonsogon.BE.jwt.JwtTokenProvider;
import ecc.sogonsogon.BE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // 닉네임 변경
    @PutMapping("/nickname")
    public ResponseEntity<String> changeNickname(@RequestBody NicknameChangeDto nicknameChangeDto,
                                                 HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization").replace("Bearer ", "");
            String userEmail = jwtTokenProvider.getUserPk(token); // JWT에서 이메일 추출

            userService.changeNickname(userEmail, nicknameChangeDto);
            return ResponseEntity.status(HttpStatus.OK).body("닉네임이 성공적으로 변경되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 비밀번호 변경
    @PutMapping("/pw")
    public ResponseEntity<String> changePassword(@RequestBody PwChangeDto passwordChangeDto,
                                                 HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization").replace("Bearer ", "");
            String userEmail = jwtTokenProvider.getUserPk(token); // JWT에서 이메일 추출

            userService.changePassword(userEmail, passwordChangeDto);
            return ResponseEntity.status(HttpStatus.OK).body("비밀번호가 성공적으로 변경되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

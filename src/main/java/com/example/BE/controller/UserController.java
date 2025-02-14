package com.example.BE.controller;

import com.example.BE.dto.PwChangeDto;
import com.example.BE.dto.NicknameChangeDto;
import com.example.BE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 비밀번호 변경
    @PutMapping("{user/pw")
    public ResponseEntity<String> changePassword(@RequestBody PwChangeDto passwordChangeDto, @RequestParam String userId) {
        try {
            userService.changePassword(userId, passwordChangeDto);
            return ResponseEntity.status(HttpStatus.OK).body("비밀번호가 성공적으로 변경되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 닉네임 변경
    @PutMapping("user/nickname")
    public ResponseEntity<String> changeNickname(@RequestBody NicknameChangeDto nicknameChangeDto, @RequestParam String userId) {
        try {
            userService.changeNickname(userId, nicknameChangeDto);
            return ResponseEntity.status(HttpStatus.OK).body("닉네임이 성공적으로 변경되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
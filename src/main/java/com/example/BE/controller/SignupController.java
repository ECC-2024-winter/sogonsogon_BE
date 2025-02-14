package com.example.BE.controller;

import com.example.BE.dto.UserDto;
import com.example.BE.entity.User;
import com.example.BE.exception.DuplicateIdException;
import com.example.BE.exception.MissingUserInfoException;
import com.example.BE.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins ="*", methods={RequestMethod.POST})
public class SignupController {
    @Autowired
    private SignupService signupService;

    @PostMapping("user/signup")
    public ResponseEntity<String> create(@RequestBody UserDto dto){
        return ResponseEntity.status(HttpStatus.OK).body("회원가입에 성공하였습니다.");
    }

    @ExceptionHandler(MissingUserInfoException.class)
    public ResponseEntity<String> handleMissingUserInfoException(MissingUserInfoException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("올바르지 않은 데이터입니다.");
    }
    @ExceptionHandler(DuplicateIdException.class)
    public ResponseEntity<String> handleDuplicateIdException(DuplicateIdException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 사용중인 아이디입니다.");
    }
}
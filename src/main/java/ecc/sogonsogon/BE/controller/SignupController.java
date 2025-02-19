package ecc.sogonsogon.BE.controller;

import ecc.sogonsogon.BE.dto.UserDto;
import ecc.sogonsogon.BE.dto.SignupResponseDto;
import ecc.sogonsogon.BE.exception.GlobalException;
import ecc.sogonsogon.BE.service.SignupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
public class SignupController {
    private final SignupService signupService;

    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping("/user/signup")
    public ResponseEntity<SignupResponseDto> create(@RequestBody UserDto dto) {
        signupService.createUser(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SignupResponseDto("회원가입에 성공하였습니다."));
    }

    @ExceptionHandler(GlobalException.UserNotFoundException.class)
    public ResponseEntity<SignupResponseDto> handleMissingUserInfoException(GlobalException.UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new SignupResponseDto("입력된 정보가 부족합니다: " + ex.getMessage()));
    }

    @ExceptionHandler(GlobalException.DuplicateEmailException.class)
    public ResponseEntity<SignupResponseDto> handleDuplicateIdException(GlobalException.DuplicateEmailException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new SignupResponseDto("이미 사용 중인 아이디입니다: " + ex.getMessage()));
    }
}
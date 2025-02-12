package com.example.BE.dto;

import com.example.BE.entity.Role;
import com.example.BE.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class UserDto {

    // 닉네임: 한국어나 영어로 3글자 이상, NotNull
    @Pattern(regexp = "^[가-힣a-zA-Z]{3,}$")
    @NotNull
    private String nickname;

    @Email(message = "유효한 이메일을 입력해주세요.")
    @NotNull
    private String email;

    // 비밀번호: 첫 글자는 영문, 8자 이상, NotNull
    @Pattern(regexp = "^[a-zA-Z][0-9a-zA-Z]{7,}$")
    @NotNull
    private String pw;

    public User toEntity() {
        // userId: 엔티티에서 자동으로 생성
        return new User(nickname, email, pw, Role.ROLE_USER);
    }
}

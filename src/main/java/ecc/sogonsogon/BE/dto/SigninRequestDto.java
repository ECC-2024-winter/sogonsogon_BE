package ecc.sogonsogon.BE.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SigninRequestDto {

    @Email(message = "유효한 이메일을 입력해주세요.")
    @NotNull(message = "이메일은 필수 입력 항목입니다.")
    private String email;

    @NotNull(message = "비밀번호는 필수 입력 항목입니다.")
    private String pw;
}
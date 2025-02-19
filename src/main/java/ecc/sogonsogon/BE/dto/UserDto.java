package ecc.sogonsogon.BE.dto;

import ecc.sogonsogon.BE.entity.Role;
import ecc.sogonsogon.BE.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    // 닉네임: 한국어나 영어로 3글자 이상, NotNull
    @Pattern(regexp = "^[가-힣a-zA-Z]{3,}$", message = "닉네임은 한글 또는 영문 3자 이상이어야 합니다.")
    @NotNull(message = "닉네임은 필수 입력 항목입니다.")
    private String nickname;

    @Email(message = "유효한 이메일을 입력해주세요.")
    @NotNull(message = "이메일은 필수 입력 항목입니다.")
    private String email;

    // 비밀번호: 첫 글자는 영문, 8자 이상, NotNull
    @Pattern(regexp = "^[a-zA-Z][0-9a-zA-Z]{7,}$", message = "비밀번호는 첫 글자가 영문이어야 하며, 최소 8자 이상이어야 합니다.")
    @NotNull(message = "비밀번호는 필수 입력 항목입니다.")
    @JsonIgnore // JSON 응답에서 숨김
    private String pw;

    // Role 이름을 입력받기 위한 필드
    @NotNull(message = "역할은 필수 입력 항목입니다.")
    private String roleName;

    public User toEntity(Role role) {
        return new User(
                null,
                this.nickname,
                this.email,
                this.pw,
                role
        );
    }
}
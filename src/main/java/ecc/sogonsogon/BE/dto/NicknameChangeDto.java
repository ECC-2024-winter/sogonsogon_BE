package ecc.sogonsogon.BE.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NicknameChangeDto {

    @Pattern(regexp = "^[가-힣a-zA-Z]{3,}$", message = "닉네임은 한글 또는 영문 3자 이상이어야 합니다.")
    @NotNull(message = "닉네임은 필수 입력 항목입니다.")
    private String newNickname;
}
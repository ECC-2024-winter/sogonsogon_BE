package ecc.sogonsogon.BE.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PwChangeDto {

    @NotNull(message = "현재 비밀번호는 필수 입력 항목입니다.")
    private String currentPw;

    @Pattern(regexp = "^[a-zA-Z][0-9a-zA-Z]{7,}$", message = "새 비밀번호는 첫 글자가 영문이어야 하며, 최소 8자 이상이어야 합니다.")
    @NotNull(message = "새 비밀번호는 필수 입력 항목입니다.")
    private String newPw;
}
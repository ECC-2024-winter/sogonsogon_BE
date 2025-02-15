package ecc.sogonsogon.BE.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SigninRequestDto {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String pw;
}
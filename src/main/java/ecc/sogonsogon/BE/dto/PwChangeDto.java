package ecc.sogonsogon.BE.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PwChangeDto {
    @NotNull
    private String oldPassword;

    @NotNull
    private String newPassword;
}
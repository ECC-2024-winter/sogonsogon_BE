package ecc.sogonsogon.BE.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JwtResponseDto {
    private String token;

    public JwtResponseDto(String token) {
        this.token = token;
    }
}
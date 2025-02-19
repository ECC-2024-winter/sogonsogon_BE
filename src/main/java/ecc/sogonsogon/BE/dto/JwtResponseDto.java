package ecc.sogonsogon.BE.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JwtResponseDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType; // "Bearer"
}

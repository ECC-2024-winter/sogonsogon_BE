package ecc.sogonsogon.BE.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SigninResponseDto {
    private String message;
    private String jwtAccessToken;
}
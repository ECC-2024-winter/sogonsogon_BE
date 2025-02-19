package ecc.sogonsogon.BE.dto;

public class UserResponseDto {
    private String message;

    public UserResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
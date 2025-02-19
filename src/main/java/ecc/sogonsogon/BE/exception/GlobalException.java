package ecc.sogonsogon.BE.exception;

public class GlobalException {

    // 사용자 관련 예외들
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    // 이메일 중복 예외
    public static class DuplicateEmailException extends RuntimeException {
        public DuplicateEmailException(String message) {
            super(message);
        }
    }

    // 닉네임 중복 예외
    public static class DuplicateNicknameException extends RuntimeException {
        public DuplicateNicknameException(String message) {
            super(message);
        }
    }

    // 비밀번호 유효성 예외
    public static class InvalidPasswordException extends RuntimeException {
        public InvalidPasswordException(String message) {
            super(message);
        }
    }

    // 역할을 찾을 수 없을 때 발생하는 예외
    public static class RoleNotFoundException extends RuntimeException {
        public RoleNotFoundException(String message) {
            super(message);
        }
    }
}
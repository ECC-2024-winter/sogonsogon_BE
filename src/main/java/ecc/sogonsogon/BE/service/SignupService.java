package ecc.sogonsogon.BE.service;

import ecc.sogonsogon.BE.dto.UserDto;
import ecc.sogonsogon.BE.entity.Role;
import ecc.sogonsogon.BE.entity.User;
import ecc.sogonsogon.BE.exception.GlobalException;
import ecc.sogonsogon.BE.exception.GlobalException.DuplicateEmailException;  // 예외 임포트
import ecc.sogonsogon.BE.repository.RoleRepository;  // RoleRepository 임포트
import ecc.sogonsogon.BE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;

@Service
public class SignupService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final RoleRepository roleRepository;  // RoleRepository 주입

    @Autowired
    public SignupService(UserRepository userRepository, BCryptPasswordEncoder encoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    public User create(UserDto dto) throws RoleNotFoundException {
        User user = dto.toEntity();

        // 필수 정보 체크
        if (user.getNickname() == null || user.getEmail() == null || user.getPassword() == null) {
            throw new GlobalException.UserNotFoundException("올바르지 않은 사용자입니다.");
        }

        // 이메일로 중복된 유저 체크
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateEmailException("이미 사용중인 이메일입니다.");
        }

        // 비밀번호 암호화
        user.setPassword(encoder.encode(user.getPassword()));

        // 기본 권한 설정 (ROLE_USER)
        if (user.getRole() == null) {
            Role role = roleRepository.findByRoleName(Role.ROLE_USER);
            if (role == null) {
                throw new RoleNotFoundException("ROLE_USER 권한을 찾을 수 없습니다.");
            }
            user.setRole(role);
        }

        return userRepository.save(user);
    }
}
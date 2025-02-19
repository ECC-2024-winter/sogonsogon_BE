package ecc.sogonsogon.BE.repository;

import ecc.sogonsogon.BE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일로 사용자 조회
    Optional<User> findByEmail(String email);

    // 닉네임으로 사용자 조회
    Optional<User> findByNickname(String nickname);

    // 이메일이 이미 존재하는지 체크하는 메서드 (회원가입 시 이메일 중복 체크용)
    boolean existsByEmail(String email);

    // 닉네임이 이미 존재하는지 체크하는 메서드 (회원가입 시 닉네임 중복 체크용)
    boolean existsByNickname(String nickname);
}
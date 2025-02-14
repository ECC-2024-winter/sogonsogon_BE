package com.example.BE.repository;

import com.example.BE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    // 이메일로 사용자를 찾는 메서드
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
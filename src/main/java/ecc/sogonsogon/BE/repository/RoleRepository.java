package ecc.sogonsogon.BE.repository;

import ecc.sogonsogon.BE.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRoleName(String roleName);  // RoleName으로 Role을 찾는 메서드
}
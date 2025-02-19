package ecc.sogonsogon.BE.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    private String roleName;

    // 기본 역할 설정
    public static final String ROLE_USER = "ROLE_USER";
}
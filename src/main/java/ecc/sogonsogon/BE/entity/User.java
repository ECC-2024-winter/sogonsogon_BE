package ecc.sogonsogon.BE.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, length = 15)
    private String nickname;

    @Column(nullable = false, unique = true)
    @Email
    @NotNull
    private String email;

    @Column(nullable = false)
    private String pw;

    @Enumerated(EnumType.STRING)
    private Role role;

    // 'nickname', 'email', 'pw', 'role'을 받는 생성자 추가
    public User(String nickname, String email, String pw, Role role) {
        this.nickname = nickname;
        this.email = email;
        this.pw = pw;
        this.role = (role != null) ? role : Role.ROLE_USER;  // role이 null이면 기본값 설정
    }

    @PrePersist
    public void prePersist() {
        // 회원가입 시 userId를 랜덤 문자열로 생성
        if (this.userId == null) {
            this.userId = generateRandomUserId();
        }
        if (this.role == null) {
            this.role = Role.ROLE_USER;
        }
    }

    private String generateRandomUserId() {
        return UUID.randomUUID().toString().substring(0,4);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(role.name()));
        return auth;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public String getPassword() {
        return pw;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
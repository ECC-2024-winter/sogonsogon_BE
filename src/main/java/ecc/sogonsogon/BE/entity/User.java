package ecc.sogonsogon.BE.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    private String userId;

    @Column(nullable = false, unique = true, length = 225)
    private String nickname;

    @Column(nullable = false, unique = true, length = 225)
    private String email;

    @Column(nullable = false, length = 225)
    private String password;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Role role;

    @PrePersist
    public void generateUserId() {
        this.userId = UUID.randomUUID().toString();
    }
}
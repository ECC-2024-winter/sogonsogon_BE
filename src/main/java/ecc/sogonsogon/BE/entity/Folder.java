package ecc.sogonsogon.BE.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Folder {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer folderId;

    @Column(name="folderName")
    private String folderName;

    @ManyToOne //유저 한 명 당 folder 여러 개
    @JoinColumn(name="userId")
    private User user;
}

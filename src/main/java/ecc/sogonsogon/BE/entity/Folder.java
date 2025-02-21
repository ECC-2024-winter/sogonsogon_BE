package ecc.sogonsogon.BE.entity;

import ecc.sogonsogon.BE.dto.FolderDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Setter
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


    public static Folder createFolder(FolderDto folderDto, User user){
        return new Folder(
                folderDto.getFolderId(),
                folderDto.getFolderName(),
                user
        );
    }
}

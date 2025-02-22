package ecc.sogonsogon.BE.dto;

import ecc.sogonsogon.BE.entity.Bookmark;
import ecc.sogonsogon.BE.entity.Folder;
import ecc.sogonsogon.BE.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FolderDto {
    private Integer folderId;
    private String folderName;
}

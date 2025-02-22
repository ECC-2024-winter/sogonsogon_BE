package ecc.sogonsogon.BE.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookmarkDto {
    private Integer bookmarkId;
    private String placeId;
    private Integer folderId;
}
package ecc.sogonsogon.BE.entity;

import ecc.sogonsogon.BE.dto.BookmarkDto;
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
public class Bookmark {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer bookmarkId;

    @ManyToOne //유저 한 명 당 bookmark가 여러 개
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne //장소 하나 당 bookmark 여러 개
    @JoinColumn(name="placeId")
    private Place place;

    @ManyToOne //폴더 하나 당 bookmark 여러 개
    @JoinColumn(name="folderId")
    private Folder folder;

    public static Bookmark creteBookmark(BookmarkDto bookmarkDto, User user, Place place, Folder folder){
        return new Bookmark(
                bookmarkDto.getBookmarkId(),
                user,
                place,
                folder
        );
    }
}

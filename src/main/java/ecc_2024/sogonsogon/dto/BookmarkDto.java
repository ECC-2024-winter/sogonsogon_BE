package ecc_2024.sogonsogon.dto;

import ecc_2024.sogonsogon.entity.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class BookmarkDto {
    private Integer id;
    private User user;
    private Place place;

    public Bookmark toEntity() {
        return new Bookmark(id, user, place);
    }
}

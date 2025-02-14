package com.example.BE.dto;

import com.example.BE.entity.Bookmark;
import com.example.BE.entity.Place;
import com.example.BE.entity.User;
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

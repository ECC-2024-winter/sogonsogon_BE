package com.example.BE.controller;

import com.example.BE.dto.BookmarkDto;
import com.example.BE.entity.Bookmark;
import com.example.BE.service.BookmarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BookmarkApiController {

    @Autowired
    private BookmarkService bookmarkService;

    // 좋아요 등록
    @PostMapping("/api/bookmark")
    public ResponseEntity<Bookmark> save(@RequestBody BookmarkDto bookmarkDto) {
        Bookmark saved = bookmarkService.save(bookmarkDto);
        return (saved!=null) ? // 저장하면 정상, 아니면 오류 발생
                ResponseEntity.status(HttpStatus.OK).body(saved) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // 좋아요 취소
}

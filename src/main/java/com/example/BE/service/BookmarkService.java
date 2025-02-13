package com.example.BE.service;

import com.example.BE.dto.BookmarkDto;
import com.example.BE.entity.Bookmark;
import com.example.BE.repository.BookmarkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookmarkService {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    public Bookmark save(BookmarkDto bookmarkDto) {
        Bookmark bookmark = bookmarkDto.toEntity();
        if(bookmark.getId()!=null){
            return null;
        }
        return bookmarkRepository.save(bookmark);
    }
}

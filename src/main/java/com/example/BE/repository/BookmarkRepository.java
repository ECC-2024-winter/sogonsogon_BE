package com.example.BE.repository;

import com.example.BE.entity.Bookmark;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookmarkRepository extends CrudRepository<Bookmark, Integer> { //북마크 ID가 Integer
    // 특정 유저의 모든 북마크 조회
    List<Bookmark> findByUserId(Integer userId);
}

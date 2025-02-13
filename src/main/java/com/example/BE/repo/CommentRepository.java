package com.example.BE.repo;

import com.example.BE.entity.Comment;
import com.example.BE.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPlace(Place place);
}
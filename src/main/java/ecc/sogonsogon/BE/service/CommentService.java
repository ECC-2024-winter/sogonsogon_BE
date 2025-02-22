package ecc.sogonsogon.BE.service;

import ecc.sogonsogon.BE.entity.Comment;
import ecc.sogonsogon.BE.repository.CommentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPlaceId(String placeId) {
        return commentRepository.findAll().stream()
                .filter(comment -> comment.getPlaceId().equals(placeId))
                .toList();
    }

    public Comment updateComment(String id, String content, int starRating) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        Comment updatedComment = Comment.builder()
                .commentId(comment.getCommentId())
                .placeId(comment.getPlaceId())
                .userId(comment.getUserId())
                .content(content)
                .starRating(starRating)
                .build();
        return commentRepository.save(updatedComment);
    }

    public void deleteComment(String id) {
        commentRepository.deleteById(id);
    }
}
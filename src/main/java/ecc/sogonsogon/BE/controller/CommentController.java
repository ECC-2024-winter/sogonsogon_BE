package ecc.sogonsogon.BE.controller;

import ecc.sogonsogon.BE.entity.Comment;
import ecc.sogonsogon.BE.service.CommentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @GetMapping("/place/{placeId}")
    public List<Comment> getCommentsByPlace(@PathVariable String placeId) {
        return commentService.getCommentsByPlaceId(placeId);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable String id, @RequestBody Comment request) {
        return commentService.updateComment(id, request.getContent(), request.getStarRating());
    }

    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
        return "댓글 삭제 완료!";
    }
}
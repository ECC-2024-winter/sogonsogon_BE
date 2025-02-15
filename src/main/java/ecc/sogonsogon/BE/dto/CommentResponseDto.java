package ecc.sogonsogon.BE.dto;

import ecc.sogonsogon.BE.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {

    private String commentId;
    private String placeId;
    private String userId;
    private float rating;
    private String content;

    public CommentResponseDto(Comment comment) {
        this.commentId = String.valueOf(comment.getCommentId());  // commentId를 String으로 변환
        this.placeId = String.valueOf(comment.getPlace().getPlaceId());  // placeId를 String으로 변환
        this.userId = comment.getUser().getUserId();
        this.rating = comment.getRating();
        this.content = comment.getContent();
    }
}
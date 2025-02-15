package ecc.sogonsogon.BE.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {

    private int placeId;
    private String userId;
    private int ratingAverage;
    private String content;
}
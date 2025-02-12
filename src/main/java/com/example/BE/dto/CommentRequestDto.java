package com.example.BE.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {

    private int placeId;
    private String userId;
    private float rating;
    private String content;
}
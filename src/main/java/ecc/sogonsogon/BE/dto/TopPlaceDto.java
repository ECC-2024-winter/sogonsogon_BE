package ecc.sogonsogon.BE.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TopPlaceDto {
    private Long placeId;
    private String placeName;
    private String category;
    private String imageUrl;
    private int reviewCount; // 조회수
    private double starAverage; // 평균 별점
}
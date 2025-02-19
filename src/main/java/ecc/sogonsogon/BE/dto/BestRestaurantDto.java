package ecc.sogonsogon.BE.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BestRestaurantDto {
    private String placeId;
    private String placeName;
    private String imageUrl;
    private double starAverage; // 평균 별점
}
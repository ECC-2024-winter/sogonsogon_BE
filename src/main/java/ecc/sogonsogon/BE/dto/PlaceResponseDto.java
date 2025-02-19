package ecc.sogonsogon.BE.dto;

import ecc.sogonsogon.BE.entity.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaceResponseDto {

    private String placeName;
    private int reviews;  // 조회수
    private double starAverage;  // 별점 평균

    // Place 엔티티를 PlaceResponseDto로 변환하는 생성자
    public PlaceResponseDto(Place place) {
        this.placeName = place.getPlaceName();
        this.reviews = place.getReviews();
        this.starAverage = place.getStarAverage();
    }
}
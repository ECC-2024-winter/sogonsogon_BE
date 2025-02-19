package ecc.sogonsogon.BE.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MainPageResponseDto {
    private List<TopPlaceDto> trendingPlaces;
    private List<BestRestaurantDto> bestRestaurants;
    private List<RandomShowDto> randomPerformances;
}
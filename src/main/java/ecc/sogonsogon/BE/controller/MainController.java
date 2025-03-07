package ecc.sogonsogon.BE.controller;

import ecc.sogonsogon.BE.dto.PlaceResponseDto;
import ecc.sogonsogon.BE.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final PlaceService placeService;

    // 조회수 기준 Top3 조회
    @GetMapping("/main/top3")
    public List<PlaceResponseDto> getTopViewedPlaces() {
        return placeService.getTop3ByReviews();
    }

    // 별점 기준 맛집 Best3 조회
    @GetMapping("/main/best3")
    public List<PlaceResponseDto> getBestRatedRestaurants() {
        return placeService.getTop3ByStarAverage();
    }

    // 공연/연극 랜덤 3개 조회
    @GetMapping("/main/show")
    public List<PlaceResponseDto> getRandomPerformances() {
        return placeService.getRandomPerformances();
    }
}
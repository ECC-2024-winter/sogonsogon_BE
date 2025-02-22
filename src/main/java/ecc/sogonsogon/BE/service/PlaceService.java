package ecc.sogonsogon.BE.service;

import ecc.sogonsogon.BE.dto.PlaceResponseDto;
import ecc.sogonsogon.BE.entity.Place;
import ecc.sogonsogon.BE.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    @Autowired
    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    // 조회수 기준 Top3 조회
    public List<PlaceResponseDto> getTop3ByReviews() {
        List<Place> places = placeRepository.findTop3ByOrderByReviewDesc();
        return places.stream()
                .map(PlaceResponseDto::new)
                .collect(Collectors.toList());
    }

    // 별점 기준 맛집 Best3 조회
    public List<PlaceResponseDto> getTop3ByStarAverage() {
        List<Place> places = placeRepository.findTop3ByCategoryOrderByStarAverageDesc();
        return places.stream()
                .map(PlaceResponseDto::new)
                .collect(Collectors.toList());
    }

    // 공연/연극 랜덤 3개 조회
    public List<PlaceResponseDto> getRandomPerformances() {
        List<Place> places = placeRepository.findRandomPerformances();
        return places.stream()
                .map(PlaceResponseDto::new)
                .collect(Collectors.toList());
    }

    public Place showPlacePageById(Integer placeId) {
        return placeRepository.findById(Long.valueOf(placeId)).orElse(null);
    }
}
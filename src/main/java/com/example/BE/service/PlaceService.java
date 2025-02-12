package com.example.BE.service;

import com.example.BE.dto.PlaceResponseDto;
import com.example.BE.entity.Place;
import com.example.BE.repo.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public List<PlaceResponseDto> getTopViewedPlaces() {
        return placeRepository.findTop3ByOrderByReviewDesc()
                .stream()
                .map(PlaceResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<PlaceResponseDto> getBestRatedRestaurants() {
        return placeRepository.findTop3ByCategoryIdOrderByStarAverageDesc(1) // 맛집 categoryId = 1
                .stream()
                .map(PlaceResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<PlaceResponseDto> getRandomPerformances() {
        return placeRepository.findRandomPerformances(2, 3) // 공연 categoryId = 2, 연극 categoryId = 3
                .stream()
                .map(PlaceResponseDto::new)
                .collect(Collectors.toList());
    }
}
package com.example.BE.dto;

import com.example.BE.entity.Place;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaceResponseDto {
    private int placeId;
    private int categoryId;
    private String placeName;
    private String imageUrl;
    private String address;
    private String openTime;
    private String contact;
    private int review;
    private float starAverage;

    public PlaceResponseDto(Place place) {
        this.placeId = place.getPlaceId();
        this.categoryId = place.getCategoryId();
        this.placeName = place.getPlaceName();
        this.imageUrl = place.getImageUrl();
        this.address = place.getAddress();
        this.openTime = place.getOpenTime();
        this.contact = place.getContact();
        this.review = place.getReview();
        this.starAverage = place.getStarAverage();
    }
}
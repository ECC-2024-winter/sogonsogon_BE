package com.example.BE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PLACES")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int placeId;

    private int categoryId;
    private String placeName;
    private String imageUrl;
    private String address;
    private String openTime;
    private String contact;
    private int review; // 조회수
    private float starAverage; // 평균 별점

}
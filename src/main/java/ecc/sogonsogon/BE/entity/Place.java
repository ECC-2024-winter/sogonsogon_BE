package ecc.sogonsogon.BE.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PLACES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
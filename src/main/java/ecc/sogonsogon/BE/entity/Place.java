package ecc.sogonsogon.BE.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // 자동으로 UUID를 생성하도록 설정
    private String placeId;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category categoryId;

    @Column(nullable = false, length = 225)
    private String placeName;

    private String imageUrl;
    private String address;
    private String openTime;
    private String contact;

    private int review; // 조회수
    private float starAverage; // 평균 별점

    @PrePersist
    public void generatePlaceId() {
        if (placeId == null) {
            placeId = UUID.randomUUID().toString();
        }
    }
}
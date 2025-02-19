package ecc.sogonsogon.BE.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RandomShowDto {
    private Long performanceId;
    private String title;
    private String imageUrl;
    private String date; // 공연 날짜
}
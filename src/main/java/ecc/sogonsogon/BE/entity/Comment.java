package ecc.sogonsogon.BE.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public String commentId;

    @ManyToOne
    @JoinColumn(name = "placeId", nullable = false)
    private Place placeId;

    private String userId;

    private String content;

    private int starRating;

    private String createdAt;

    private String updatedAt;

    public void updateContent(String content, int starRating) {
        this.content = content;
        this.starRating = starRating;
    }
}

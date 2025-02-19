package ecc.sogonsogon.BE.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String categoryId;

    @Column(nullable = false, unique = true, length = 225)
    private String categoryName;
}
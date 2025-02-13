package com.example.BE.repo;

import com.example.BE.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {

    // 조회수 기준 Top 3 조회
    List<Place> findTop3ByOrderByReviewDesc();

    // 별점 기준 맛집 Best 3 조회
    List<Place> findTop3ByCategoryIdOrderByStarAverageDesc(int categoryId);

    // 공연/연극 랜덤 3개 조회 (데이터 소스 작성 필요)
    @Query(value = "SELECT * FROM PLACES WHERE category_id IN (?1, ?2) ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<Place> findRandomPerformances(int performanceCategoryId, int theaterCategoryId);
}
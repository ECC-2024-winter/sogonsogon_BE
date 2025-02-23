package ecc.sogonsogon.BE.repository;

import ecc.sogonsogon.BE.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, String> {

    // 특정 카테고리에 속하는 장소 조회
    List<Place> findByCategories_CategoryName(String categoryName);

    // 조회수가 높은 3개 장소
    @Query("SELECT p FROM Place p ORDER BY p.review DESC LIMIT 3")
    List<Place> findTop3ByReview();

    // 별점이 높은 3개 맛집
    @Query("SELECT p FROM Place p WHERE '맛집' MEMBER OF p.categories ORDER BY p.starAverage DESC LIMIT 3")
    List<Place> findBest3Restaurants();

    // 공연 카테고리에서 랜덤 3개
    @Query("SELECT p FROM Place p WHERE '공연' MEMBER OF p.categories ORDER BY FUNCTION('RAND') LIMIT 3")
    List<Place> findRandomShows();
}
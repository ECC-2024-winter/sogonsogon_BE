package ecc.sogonsogon.BE.repository;

import ecc.sogonsogon.BE.entity.Place;
import ecc.sogonsogon.BE.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    // 조회수 top3
    @Query("SELECT p FROM Place p ORDER BY p.review DESC")
    List<Place> findTop3ByOrderByReviewDesc();

    // 별점 best3
    @Query("SELECT p FROM Place p WHERE p.categoryID = :category ORDER BY p.starAverage DESC")
    List<Place> findTop3ByCategoryOrderByStarAverageDesc(Category category);

    // 공연, 연극 랜덤 3
    @Query("SELECT p FROM Place p WHERE p.category IN (:performanceCategory, :theaterCategory) ORDER BY FUNCTION('RAND')")
    List<Place> findRandomPerformances(Category performanceCategory, Category theaterCategory);

    //특정 카테고리명을 가진 장소 조회
    @Query("SELECT p FROM Place p JOIN p.categories c WHERE c.categoryName IN :categoryNames GROUP BY p HAVING COUNT(DISTINCT c) = :size")
    List<Place> findByCategoryNames(@Param("categoryNames") List<String> categoryNames, @Param("size") long size);
}
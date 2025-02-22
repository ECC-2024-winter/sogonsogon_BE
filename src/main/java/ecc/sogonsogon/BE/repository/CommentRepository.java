package ecc.sogonsogon.BE.repository;

import ecc.sogonsogon.BE.entity.Comment;
import ecc.sogonsogon.BE.entity.Place;
import ecc.sogonsogon.BE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository <Comment, Long> {
    List<Comment> findByPlace(Place place);
    List<Comment> findByUser(User user);
}
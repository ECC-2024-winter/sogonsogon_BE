package ecc.sogonsogon.BE.repository;

import ecc.sogonsogon.BE.entity.Bookmark;
import ecc.sogonsogon.BE.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    List<Bookmark> findByFolder(Folder folder);

}
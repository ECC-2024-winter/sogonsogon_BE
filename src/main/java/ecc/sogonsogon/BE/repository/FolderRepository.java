package ecc.sogonsogon.BE.repository;

import ecc.sogonsogon.BE.entity.Folder;
import ecc.sogonsogon.BE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {
    @Override
    ArrayList<Folder> findAll();

    List<Folder> findByUser(User user);

    Optional<Folder> findByUserAndFolderId(User user, Integer folderId);
}
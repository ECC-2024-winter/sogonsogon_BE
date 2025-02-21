package ecc.sogonsogon.BE.repository;

import ecc.sogonsogon.BE.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {
    @Override
    ArrayList<Folder> findAll();
}

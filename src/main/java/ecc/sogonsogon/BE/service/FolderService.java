package ecc.sogonsogon.BE.service;

import ecc.sogonsogon.BE.dto.FolderDto;
import ecc.sogonsogon.BE.entity.Folder;
import ecc.sogonsogon.BE.entity.User;
import ecc.sogonsogon.BE.repository.FolderRepository;
import ecc.sogonsogon.BE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolderService {
    @Autowired
    private FolderRepository folderRepository;


    @Autowired
    private UserRepository userRepository;


    public Folder create(User user, FolderDto folderDto) {
        Folder folder = Folder.createFolder(folderDto,user);
        return folderRepository.save(folder);
    }
}

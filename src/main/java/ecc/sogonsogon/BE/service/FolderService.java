package ecc.sogonsogon.BE.service;

import ecc.sogonsogon.BE.dto.FolderDto;
import ecc.sogonsogon.BE.entity.Folder;
import ecc.sogonsogon.BE.entity.User;
import ecc.sogonsogon.BE.repository.FolderRepository;
import ecc.sogonsogon.BE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Folder delete(User user,Integer id) {
        //1. 대상 찾기
        Folder target = folderRepository.findById(id).orElse(null);
        //2. 잘못된 요청 처리
        if(target == null || !target.getUser().equals(user)) {
            throw new IllegalArgumentException("삭제할 수 없습니다.");
        }
        //3. 대상 삭제
        folderRepository.delete(target);
        return target;
    }

    public Folder update(User user, Integer id,FolderDto folderDto) {

        Folder target=folderRepository.findById(id).orElse(null);
        //3. 잘못된 요청 처리
        if(target == null || !target.getUser().equals(user)) {
            throw new IllegalArgumentException("폴더 이름을 수정할 수 없습니다.");
        }
        //4. 업데이트 및 정상 응답
        if (folderDto.getFolderName() != null) {
            target.setFolderName(folderDto.getFolderName());
        }
        return folderRepository.save(target);

    }

    public List<Folder> showFolders(User user) {return folderRepository.findByUser(user);}


}
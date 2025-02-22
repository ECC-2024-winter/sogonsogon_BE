package ecc.sogonsogon.BE.controller;

import ecc.sogonsogon.BE.entity.Bookmark;
import ecc.sogonsogon.BE.entity.Folder;
import ecc.sogonsogon.BE.entity.User;
import ecc.sogonsogon.BE.jwt.JwtTokenProvider;
import ecc.sogonsogon.BE.repository.FolderRepository;
import ecc.sogonsogon.BE.service.UserService;
import ecc.sogonsogon.BE.service.FolderService;
import ecc.sogonsogon.BE.service.BookmarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Controller
public class FolderController {
    @Autowired
    private JwtTokenProvider jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private FolderService folderService;
    @Autowired
    private BookmarkService bookmarkService;

    @GetMapping("/folders")
    public String showFolders(@RequestHeader("Authorization") String token, Model model) {
        String jwt = token.substring(7);
        String email = jwtUtil.extractEmail(jwt);

        User user = userService.findUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 유저를 찾을 수 없습니다."));
        //1. 모든 폴더 데이터 가져오기
        List<Folder> folderEntityList = folderService.showFolders(user);
        //2. 모델에 데이터 등록하기
        model.addAttribute("folderList",folderEntityList);
        //3. 뷰 페이지 설정
        return "";
    }

    @GetMapping("/folders/{id}")
    public String showBookmarks(@RequestHeader("Authorization") String token,@PathVariable int id, Model model) {
        String jwt = token.substring(7);
        String email = jwtUtil.extractEmail(jwt);

        User user = userService.findUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 유저를 찾을 수 없습니다."));
        //1. id 조회해 데이터 가져오기
        Folder folderEntity = folderRepository.findByUserAndFolderId(user,id).orElse(null);
        List<Bookmark> bookmarks = bookmarkService.showBookmarksInFolder(user,folderEntity);
        //2. 모델에 데이터 등록
        model.addAttribute("folder",folderEntity);
        model.addAttribute("bookmarks",bookmarks);
        //3. 뷰 페이지 반환
        return "";
    }
}

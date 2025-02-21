package ecc.sogonsogon.BE.controller;

import ecc.sogonsogon.BE.dto.FolderDto;
import ecc.sogonsogon.BE.entity.Folder;
import ecc.sogonsogon.BE.entity.User;
import ecc.sogonsogon.BE.jwt.JwtTokenProvider;
import ecc.sogonsogon.BE.service.FolderService;
import ecc.sogonsogon.BE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class FolderApiController {
    @Autowired
    private FolderService folderService;
    @Autowired
    private JwtTokenProvider jwtUtil;
    @Autowired
    private UserService userService;


    // 폴더 생성
    @PostMapping("/folders")
    public ResponseEntity<Folder> create(@RequestHeader("Authorization") String token, @RequestBody FolderDto folderDto) {

        String jwt = token.substring(7);
        String email = jwtUtil.extractEmail(jwt);

        User user = userService.findUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 유저를 찾을 수 없습니다."));

        Folder created =folderService.create(user,folderDto);
        return (created!=null)?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 폴더 삭제
    @DeleteMapping("/folders/{id}")
    public ResponseEntity<Folder> delete(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        String jwt = token.substring(7);
        String email = jwtUtil.extractEmail(jwt);

        User user = userService.findUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 유저를 찾을 수 없습니다."));

        Folder deleted = folderService.delete(user,id);
        return (deleted!=null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 폴더 이름 변경
    @PatchMapping("/folders/{id}")
    public ResponseEntity<Folder> update(@RequestHeader("Authorization") String token, @PathVariable Integer id, @RequestBody FolderDto folderDto) {
        String jwt = token.substring(7);
        String email = jwtUtil.extractEmail(jwt);

        User user = userService.findUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 유저를 찾을 수 없습니다."));

        Folder updated = folderService.update(user, id, folderDto);
        return (updated!=null)?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

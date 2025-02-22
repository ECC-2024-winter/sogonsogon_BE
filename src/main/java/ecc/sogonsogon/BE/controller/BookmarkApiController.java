package ecc.sogonsogon.BE.controller;

import ecc.sogonsogon.BE.dto.BookmarkDto;
import ecc.sogonsogon.BE.entity.Bookmark;
import ecc.sogonsogon.BE.entity.User;
import ecc.sogonsogon.BE.repository.UserRepository;
import ecc.sogonsogon.BE.security.JwtUtil;
import ecc.sogonsogon.BE.service.BookmarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
public class BookmarkApiController {
    @Autowired
    private BookmarkService bookmarkService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/bookmarks")
    public ResponseEntity<Bookmark> save(@RequestHeader("Authorization") String token, @RequestBody BookmarkDto bookmarkDto) {

        String jwt = token.substring(7);
        String email = jwtUtil.extractEmail(jwt);

        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 유저를 찾을 수 없습니다."));

        Bookmark saved = bookmarkService.save(user, bookmarkDto);
        return (saved!=null) ?
                ResponseEntity.status(HttpStatus.OK).body(saved) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/bookmarks/{id}")
    public ResponseEntity<Bookmark> delete(@RequestHeader("Authorization") String token, @PathVariable Integer id) {

        String jwt = token.substring(7);
        String email = jwtUtil.extractEmail(jwt);

        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 유저를 찾을 수 없습니다."));

        Bookmark deleted = bookmarkService.delete(user, id);
        return (deleted!=null)?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() : //북마크가 있다면 삭제됐으니 no content
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //북마크가 없다면 삭제 못했다는 뜻
    }
}

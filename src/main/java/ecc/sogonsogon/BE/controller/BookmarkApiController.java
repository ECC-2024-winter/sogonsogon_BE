package ecc.sogonsogon.BE.controller;

import ecc.sogonsogon.BE.dto.BookmarkDto;
import ecc.sogonsogon.BE.entity.Bookmark;
import ecc.sogonsogon.BE.entity.User;
import ecc.sogonsogon.BE.jwt.JwtTokenProvider;
import ecc.sogonsogon.BE.service.BookmarkService;
import ecc.sogonsogon.BE.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
public class BookmarkApiController {
    @Autowired
    private BookmarkService bookmarkService;
    @Autowired
    private JwtTokenProvider jwtUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/bookmarks")
    public ResponseEntity<Bookmark> save(@RequestHeader("Authorization") String token, @RequestBody BookmarkDto bookmarkDto) {

        String jwt = token.substring(7);
        String email = jwtUtil.extractEmail(jwt);

        User user = userService.findUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 유저를 찾을 수 없습니다."));

        Bookmark saved = bookmarkService.save(user, bookmarkDto);
        return (saved!=null) ?
                ResponseEntity.status(HttpStatus.OK).body(saved) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

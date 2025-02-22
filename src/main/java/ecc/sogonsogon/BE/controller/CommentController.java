package ecc.sogonsogon.BE.controller;

import ecc.sogonsogon.BE.entity.Comment;
import ecc.sogonsogon.BE.entity.User;
import ecc.sogonsogon.BE.jwt.JwtTokenProvider;
import ecc.sogonsogon.BE.repository.UserRepository;
import ecc.sogonsogon.BE.service.CommentService;
import ecc.sogonsogon.BE.service.UserService;
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
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenProvider jwtUtil;
    @Autowired
    private UserService userService;

    @GetMapping("mypage/comment")
    public String showCommentsOfUser(@RequestHeader("Authorization") String token, Model model) {
        String jwt = token.substring(7);
        String email = jwtUtil.extractEmail(jwt);

        User user = userService.findUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당 유저를 찾을 수 없습니다."));

        List<Comment> comments = commentService.showCommentsOfUser(user);
        model.addAttribute("comments", comments);
        return "";
    }
}

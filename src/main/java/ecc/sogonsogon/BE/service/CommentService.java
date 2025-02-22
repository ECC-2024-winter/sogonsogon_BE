package ecc.sogonsogon.BE.service;

import ecc.sogonsogon.BE.entity.Comment;
import ecc.sogonsogon.BE.entity.User;
import ecc.sogonsogon.BE.repository.CommentRepository;
import ecc.sogonsogon.BE.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    //특정 유저의 모든 댓글 조회

    @Transactional
    public List<Comment> showCommentsOfUser(User user){
        return commentRepository.findByUser(user);
    }
}

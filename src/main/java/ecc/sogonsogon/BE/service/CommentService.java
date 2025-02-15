package ecc.sogonsogon.BE.service;

import ecc.sogonsogon.BE.dto.CommentRequestDto;
import ecc.sogonsogon.BE.dto.CommentResponseDto;
import ecc.sogonsogon.BE.entity.Comment;
import ecc.sogonsogon.BE.entity.Place;
import ecc.sogonsogon.BE.entity.User;
import ecc.sogonsogon.BE.repository.CommentRepository;
import ecc.sogonsogon.BE.repository.PlaceRepository;
import ecc.sogonsogon.BE.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    // 특정 장소의 댓글 조회
    public List<CommentResponseDto> getCommentsByPlace(int placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 장소를 찾을 수 없습니다."));
        return commentRepository.findByPlace(place)
                .stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    // 댓글 작성
    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto) {
        Place place = placeRepository.findById(requestDto.getPlaceId())
                .orElseThrow(() -> new IllegalArgumentException("해당 장소를 찾을 수 없습니다."));
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        Comment comment = new Comment();
        comment.setPlace(place);
        comment.setUser(user);
        comment.setRating(requestDto.getRating());
        comment.setContent(requestDto.getContent());

        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    // 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        comment.setRating(requestDto.getRating());
        comment.setContent(requestDto.getContent());

        return new CommentResponseDto(comment);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        commentRepository.delete(comment);
    }
}
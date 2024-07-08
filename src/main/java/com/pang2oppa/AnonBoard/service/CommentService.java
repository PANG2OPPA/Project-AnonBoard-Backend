package com.pang2oppa.AnonBoard.service;

import com.pang2oppa.AnonBoard.dto.CommentDto;
import com.pang2oppa.AnonBoard.entity.Board;
import com.pang2oppa.AnonBoard.entity.Comment;
import com.pang2oppa.AnonBoard.entity.User;
import com.pang2oppa.AnonBoard.repository.BoardRepository;
import com.pang2oppa.AnonBoard.repository.CommentRepository;
import com.pang2oppa.AnonBoard.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    // 댓글 조회
    public List<CommentDto> getCommentsByBoardId(Long boardId) {
        List<Comment> comments = commentRepository.findByBoardId(boardId);
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            commentDtos.add(convertEntityToDto(comment));
        }
        return commentDtos;
    }

    // 댓글 작성
    public boolean writeComment(Long boardId, CommentDto commentDto) {
        try {
            User user = userRepository.findByUserId(commentDto.getUser()).orElseThrow(
                    () -> new RuntimeException("해당 회원이 존재하지 않습니다.")
            );
            Board board = boardRepository.findById(boardId).orElseThrow(
                    () -> new RuntimeException("해당 게시글이 존재하지 않습니다.")
            );
            Comment comment = new Comment();
            comment.setContent(commentDto.getContent());
            comment.setUser(user);
            comment.setBoard(board);
            comment.setRegDate(LocalDateTime.now());
            commentRepository.save(comment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 댓글 삭제
    public boolean deleteComment(Long boardId, Long commentId) {
        try {
            Comment comment = commentRepository.findById(commentId).orElseThrow(
                    () -> new RuntimeException("해당 댓글이 존재하지 않습니다.")
            );

            if (!comment.getBoard().getId().equals(boardId)) {
                throw new RuntimeException("해당 게시판에 속한 댓글이 아닙니다.");
            }

            commentRepository.deleteById(commentId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private CommentDto convertEntityToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setUser(comment.getUser().getUserId());
        commentDto.setBoard(comment.getBoard().getId());
        commentDto.setRegDate(comment.getRegDate());
        return commentDto;
    }
}

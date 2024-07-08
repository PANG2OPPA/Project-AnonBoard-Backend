package com.pang2oppa.AnonBoard.controller;

import com.pang2oppa.AnonBoard.dto.BoardDto;
import com.pang2oppa.AnonBoard.dto.CommentDto;
import com.pang2oppa.AnonBoard.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 조회
    @GetMapping("/list/{boardId}")
    public ResponseEntity<List<CommentDto>> CommentByBoardId(@PathVariable Long boardId){
        List<CommentDto> list = commentService.getCommentsByBoardId(boardId);
        return ResponseEntity.ok(list);
    }

    // 댓글 작성
    @PostMapping("/write/{boardId}")
    public ResponseEntity<Boolean> commentWrite(@PathVariable Long boardId, @RequestBody CommentDto commentDto) {
        boolean isTrue = commentService.writeComment(boardId, commentDto);
        return ResponseEntity.ok(isTrue);
    }



    // 댓글 삭제
    @DeleteMapping("/delete/{boardId}/{commentId}")
    public ResponseEntity<Boolean> commentDelete(@PathVariable Long boardId, @PathVariable Long commentId) {
        boolean isTrue = commentService.deleteComment(boardId, commentId);
        return ResponseEntity.ok(isTrue);
    }
}

package com.pang2oppa.AnonBoard.controller;

import com.pang2oppa.AnonBoard.dto.BoardDto;
import com.pang2oppa.AnonBoard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 전체 게시글 조회
    @GetMapping("/list")
    public ResponseEntity<List<BoardDto>> boardList() {
        List<BoardDto> list = boardService.getBoardList();
        return ResponseEntity.ok(list);
    }

    // 게시글 페이지 조회
    @GetMapping("/list/page")
    public ResponseEntity<List<BoardDto>> boardListByPage(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        List<BoardDto> list = boardService.getBoardListByPage(page, size);
        log.info("list : {}", list);
        return ResponseEntity.ok(list);
    }

    // 전체 페이지 조회
    @GetMapping("/list/pages")
    public ResponseEntity<Integer> getTotalPages(@RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(0, size);
        int totalPages = boardService.getBoardPage(pageable);
        return ResponseEntity.ok(totalPages);
    }

    // 나의 게시글 조회
    @GetMapping("/list/{userId}")
    public ResponseEntity<List<BoardDto>> BoardByUserId(@PathVariable Long userId){
        List<BoardDto> list = boardService.getBoardsByUserId(userId);
        return ResponseEntity.ok(list);
    }

    // 게시글 상세 조회
    @GetMapping("/detail/{id}")
    public ResponseEntity<BoardDto> getBoardDetail(@PathVariable Long id) {
        BoardDto board = boardService.getBoardDetail(id);
        return ResponseEntity.ok(board);
    }

    // 게시글 작성
    @PostMapping("/write")
    public ResponseEntity<Boolean> boardWrite(@RequestBody BoardDto boardDto) {
        boolean isTrue = boardService.writeBoard(boardDto);
        return ResponseEntity.ok(isTrue);
    }

    // 게시글 수정
    @PutMapping("/modify/{id}")
    public ResponseEntity<Boolean> boardUpdate(@PathVariable Long id, @RequestBody BoardDto boardDto) {
        boolean isTrue = boardService.updateBoard(id, boardDto);
        return ResponseEntity.ok(isTrue);
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> boardDelete(@PathVariable Long id) {
        boolean isTrue = boardService.deleteBoard(id);
        return ResponseEntity.ok(isTrue);
    }


}

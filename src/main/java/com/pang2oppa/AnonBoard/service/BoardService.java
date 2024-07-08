package com.pang2oppa.AnonBoard.service;

import com.pang2oppa.AnonBoard.dto.BoardDto;
import com.pang2oppa.AnonBoard.dto.UserDto;
import com.pang2oppa.AnonBoard.entity.Board;
import com.pang2oppa.AnonBoard.entity.User;
import com.pang2oppa.AnonBoard.repository.BoardRepository;
import com.pang2oppa.AnonBoard.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // 전체 게시글 조회
    public List<BoardDto> getBoardList() {
        List<Board> boards = boardRepository.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();
        for (Board board : boards) {
            boardDtos.add(convertEntityToDto(board));
        }
        return boardDtos;
    }

    // 나의 게시글 조회
    public List<BoardDto> getBoardsByUserId(Long userId) {
        List<Board> boards = boardRepository.findByUserId(userId);
        List<BoardDto> boardDtos = new ArrayList<>();
        for (Board board : boards) {
            boardDtos.add(convertEntityToDto(board));
        }
        return boardDtos;
    }

    // 게시글 상세 조회
    public BoardDto getBoardDetail(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("해당 게시글이 존재하지 않습니다.")
        );
        return convertEntityToDto(board);
    }

    // 게시글 작성
    public boolean writeBoard(BoardDto boardDto) {
        try {
            Board board = new Board();
            User user = userRepository.findByUserId(boardDto.getUser()).orElseThrow(
                    () -> new RuntimeException("해당 회원이 존재하지 않습니다.")
            );
            board.setTitle(boardDto.getTitle());
            board.setUser(user);
            board.setContent(boardDto.getContent());
            boardRepository.save(board);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 게시글 수정
    public boolean updateBoard(Long id, BoardDto boardDto) {
        try {
            Board board = boardRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("해당 게시글이 존재하지 않습니다.")
            );
            if(boardDto.getTitle()!=null){
                board.setTitle(boardDto.getTitle());}
            if(boardDto.getContent()!=null){
                board.setContent(boardDto.getContent());}
            boardRepository.save(board);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 게시글 삭제
    public boolean deleteBoard(Long id) {
        try {
            boardRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private BoardDto convertEntityToDto(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(board.getId());
        boardDto.setTitle(board.getTitle());
        boardDto.setUser(board.getUser().getUserId());
        boardDto.setContent(board.getContent());
        boardDto.setRegDate(board.getRegDate());
        return boardDto;
    }

}

package com.pang2oppa.AnonBoard.repository;

import com.pang2oppa.AnonBoard.entity.Board;
import com.pang2oppa.AnonBoard.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByUserId(Long userId);

}

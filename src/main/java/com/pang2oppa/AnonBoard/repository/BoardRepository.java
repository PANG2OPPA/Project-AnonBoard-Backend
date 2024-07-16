package com.pang2oppa.AnonBoard.repository;

import com.pang2oppa.AnonBoard.entity.Board;
import com.pang2oppa.AnonBoard.entity.Comment;
import com.pang2oppa.AnonBoard.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByUser(User user);
    Page<Board> findAll(Pageable pageable);
}

package com.pang2oppa.AnonBoard.repository;

import com.pang2oppa.AnonBoard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

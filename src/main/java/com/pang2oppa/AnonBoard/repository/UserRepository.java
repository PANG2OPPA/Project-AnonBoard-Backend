package com.pang2oppa.AnonBoard.repository;

import com.pang2oppa.AnonBoard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndPassword(String userId, String password);

    Optional<User> findByUserId(String userId);

    boolean existsByUserId(String userId);

    void deleteByUserId(String userId);
}

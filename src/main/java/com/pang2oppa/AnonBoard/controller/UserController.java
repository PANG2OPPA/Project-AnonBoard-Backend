package com.pang2oppa.AnonBoard.controller;

import com.pang2oppa.AnonBoard.dto.UserDto;
import com.pang2oppa.AnonBoard.entity.User;
import com.pang2oppa.AnonBoard.repository.UserRepository;
import com.pang2oppa.AnonBoard.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto requestDto) {
        return ResponseEntity.ok(userService.signup(requestDto));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto requestDto) {
        return ResponseEntity.ok(userService.signin(requestDto));
    }
}

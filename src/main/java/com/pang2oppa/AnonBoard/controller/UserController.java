package com.pang2oppa.AnonBoard.controller;

import com.pang2oppa.AnonBoard.dto.UserDto;
import com.pang2oppa.AnonBoard.entity.User;
import com.pang2oppa.AnonBoard.repository.UserRepository;
import com.pang2oppa.AnonBoard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody UserDto requestDto) {
        Map<String, String> response = new HashMap<>();
        try {
            String message = userService.signup(requestDto);
            response.put("message", message);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // 로그인
    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> signin(@RequestBody UserDto requestDto) {
        Map<String, String> response = new HashMap<>();
        try {
            userService.signin(requestDto);
            response.put("message", "로그인을 성공하였습니다.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", "로그인을 실패하였습니다. " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    // 회원 탈퇴
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Boolean> userDelete(@PathVariable String userId) {
        boolean isTrue = userService.deleteUser(userId);
        return ResponseEntity.ok(isTrue);
    }

    // 회원 조회
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> userList() {
        List<UserDto> list = userService.getUserList();
        return ResponseEntity.ok(list);
    }


    // 중복 확인
    @GetMapping("/exists/{userId}")
    public ResponseEntity<Boolean> memberExists(@PathVariable String userId) {
        log.info("userId: {}", userId);
        boolean isTrue = userService.isUser(userId);
        return ResponseEntity.ok(!isTrue);
    }
}

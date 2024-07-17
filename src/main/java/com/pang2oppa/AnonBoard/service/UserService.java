package com.pang2oppa.AnonBoard.service;

import com.pang2oppa.AnonBoard.dto.UserDto;
import com.pang2oppa.AnonBoard.entity.User;
import com.pang2oppa.AnonBoard.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public String signup(UserDto requestDto) {
        if (userRepository.existsByUserId(requestDto.getUserId())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        requestDto.setPassword(encodedPassword);

        // UserDto를 User 엔티티로 변환
        User user = requestDto.toEntity();
        userRepository.save(user);

        return "가입을 완료하였습니다.";
    }

    // 로그인
    public UserDto signin(UserDto requestDto) {
        User user = userRepository.findByUserId(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("가입되지 않은 아이디입니다."));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return UserDto.of(user);
    }

    // 회원 탈퇴
    @Transactional
    public boolean deleteUser(String userId) {
        try {
            userRepository.deleteByUserId(userId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 전체 회원 조회
    public List<UserDto> getUserList() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(convertEntityToDto(user));
        }
        return userDtos;
    }

    public boolean isUser(String userId) {
        return userRepository.existsByUserId(userId);
    }

    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setTel(user.getTel());
        userDto.setRegDate(user.getRegDate());
        return userDto;
    }
}

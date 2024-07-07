package com.pang2oppa.AnonBoard.dto;

import com.pang2oppa.AnonBoard.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private long id;
    private String userId;
    private String password;
    private String name;
    private String tel;
    private LocalDateTime regDate;

    public static UserDto of(User user) {
        return UserDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .password(user.getPassword())
                .name(user.getName())
                .tel(user.getTel())
                .regDate(user.getRegDate())
                .build();
    }

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .tel(tel)
                .build();
    }
}

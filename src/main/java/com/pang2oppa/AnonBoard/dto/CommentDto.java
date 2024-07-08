package com.pang2oppa.AnonBoard.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private long id;
    private String content;
    private String user;
    private Long board;
    private LocalDateTime regDate;
}

package com.pang2oppa.AnonBoard.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {
    private long id;
    private String title;
    private String user;
    private String content;
    private LocalDateTime regDate;

}

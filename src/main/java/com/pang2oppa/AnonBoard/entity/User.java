package com.pang2oppa.AnonBoard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "User_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_profile_seq")
    private Long id;
    @Column(unique = true, nullable = false)
    private String userId;
    private String password;
    private String name;
    private String tel;
    private LocalDateTime regDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> boards;

    @PrePersist
    protected void prePersist() {
        regDate = LocalDateTime.now();
    }

    @Builder
    public User(String userId, String password, String name, String tel) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.regDate = LocalDateTime.now();
    }
}

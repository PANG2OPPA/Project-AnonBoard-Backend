package com.pang2oppa.AnonBoard.config;

import com.pang2oppa.AnonBoard.entity.User;
import com.pang2oppa.AnonBoard.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository) {
        return args -> {
            User user = new User();
            user.setUsername("testuser");
            user.setPassword("password");
            userRepository.save(user);
        };
    }
}

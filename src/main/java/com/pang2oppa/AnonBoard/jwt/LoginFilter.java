package com.pang2oppa.AnonBoard.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public LoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/user/signin"); // 로그인 엔드포인트 설정
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Map<String, String> credentials = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            String userId = credentials.get("userId");
            String password = credentials.get("password");

            if (userId == null || password == null) {
                System.out.println("UserId or password is null");
                throw new AuthenticationException("UserId or password is null") {};
            }

            System.out.println("로그인 시도 사용자: " + userId);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userId, password);

            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        System.out.println("로그인 성공!");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        System.out.println("로그인 실패: " + failed.getMessage());
    }
}

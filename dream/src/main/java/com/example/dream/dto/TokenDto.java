package com.example.dream.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenDto {
    private String accessToken;
    private String refreshToken;

    private int statusCode;

    private String msg;
    private String nickname;


    public TokenDto(String accessToken, String refreshToken, String nickname, int statusCode, String msg) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.statusCode = statusCode;
        this.msg = msg;
        this.nickname = nickname;
    }

    public TokenDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}


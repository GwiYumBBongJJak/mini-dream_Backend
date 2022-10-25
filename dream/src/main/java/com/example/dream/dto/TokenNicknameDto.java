package com.example.dream.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class TokenNicknameDto {
    private String accessToken;
    private String refreshToken;

    private int status;
    private String nickname;


    public TokenNicknameDto(String accessToken, String refreshToken, String nickname, int status) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.status = status;
        this.nickname = nickname;
    }
}
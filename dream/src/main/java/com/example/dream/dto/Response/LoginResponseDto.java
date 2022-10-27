package com.example.dream.dto.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class LoginResponseDto {

    private String msg;
    private int statusCode;

    public LoginResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
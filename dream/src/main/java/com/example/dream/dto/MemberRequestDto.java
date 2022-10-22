package com.example.dream.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class MemberRequestDto {
    @NotBlank
    private String username;

    @NotBlank
    private String nickname;
    @NotBlank
    private String password;

    public MemberRequestDto(String username, String nickname ,String password) {
        this.username = username;
        this.nickname = nickname;
        this.password = password;
    }
    public void setPasswordEncoder(String encodedPassword){
        this.password = encodedPassword;
    }
}

package com.example.dream.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class MemberRequestDto {

    @NotBlank(message = "이름을 입력해주세요.")
    @Pattern(regexp = "[a-zA-Z0-9]{5,15}", message = "이름을 영어(대문자 포함)와 숫자를 포함해서 5~15자리 이내로 입력해주세요.")
    private String username;

    @NotBlank(message = "닉네임을 입력해주세요.")
    //@Size(min = 4, max = 12)
    //@Pattern(regexp = "[a-zA-Z0-9]{4,12}", message = "닉네임을 영어(대문자 포함)와 숫자를 포함해서 4~12자리 이내로 입력해주세요.")
    private String nickname;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "[a-z0-9]{8,16}", message = "비밀번호를 소문자(대문자 미포함)와 숫자를 포함해서 8~16자리 이내로 입력해주세요.")
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












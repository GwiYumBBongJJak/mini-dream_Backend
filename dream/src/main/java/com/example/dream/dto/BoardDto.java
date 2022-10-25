package com.example.dream.dto;

import com.example.dream.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor


public class BoardDto {

    @NotBlank
    private String boardTitle;
    @NotBlank
    private String boardContent;

//    private Member member;
//    @NotBlank
//    private String nickname;
//    @NotBlank
//    private String username;

    public BoardDto(String title, String content) {
        this.boardTitle = title;
        this.boardContent = content;
    }
}


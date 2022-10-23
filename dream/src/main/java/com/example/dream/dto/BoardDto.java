package com.example.dream.dto;

import com.example.dream.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {
    private String board_title;
    private String board_content;
    private Member member;

    public BoardDto(String title, String content, Member member) {
        this.board_title = title;
        this.board_content = content;
        this.member = member;
    }
}

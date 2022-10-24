package com.example.dream.dto;

import com.example.dream.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private String title;
    private String content;
    private String nickname;
    // private List<Comment> comments;


    public BoardResponseDto(Board board) {
        this.title = board.getBoard_title();
        this.content = board.getBoard_content();
        this.nickname = board.getMember().getNickname();
        // this.comments = board.getComments();
    }

}

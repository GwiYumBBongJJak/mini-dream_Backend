package com.example.dream.dto;

import com.example.dream.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private String boardTitle;
    private String boardContent;
    private String nickname;
    private Long boardId;
    // private List<Comment> comments;


    public BoardResponseDto(Board board) {
        this.boardId = board.getBoard_id();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.nickname = board.getMember().getNickname();
        // this.comments = board.getComments();
    }

}

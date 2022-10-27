package com.example.dream.dto;

import com.example.dream.entity.Board;
import com.example.dream.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Getter
@NoArgsConstructor
public class BoardDetailDto {

    private String boardTitle;
    private String boardContent;
    private String nickname;
    private Long memberID;
    private Long boardId;

    List<Comment> boardDetailDto = new LinkedList<>();

    public BoardDetailDto(Board board) {
        this.boardId = board.getBoardId();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.nickname = board.getMember().getNickname();
        this.memberID = board.getMember().getMemberId();
    }

}

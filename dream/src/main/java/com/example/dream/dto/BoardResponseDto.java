package com.example.dream.dto;

import com.example.dream.entity.Board;
import com.example.dream.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class BoardResponseDto {
    private String boardTitle;
    private String boardContent;
    private String nickname;
    private Long memberID;
    private Long boardId;

    List<Comment> comments;
//
//    private int likeCount;
//
//    private int dislikeCount;
//
//    private int horrorCount;



    public BoardResponseDto(Board board) {
        this.boardId = board.getBoardId();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.nickname = board.getMember().getNickname();
        this.memberID = board.getMember().getMember_id();

//        this.likeCount = board.getLikeCount();
//        this.dislikeCount = board.getDislikeCount();
//        this.horrorCount = board.getHorrorCount();
        // this.comments = board.getComments();
    }
    public BoardResponseDto(Board board, List<Comment> commentList) {
        this.boardId = board.getBoardId();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.nickname = board.getMember().getNickname();
        this.memberID = board.getMember().getMember_id();
        this.comments = commentList;
//        this.likeCount = board.getLikeCount();
//        this.dislikeCount = board.getDislikeCount();
//        this.horrorCount = board.getHorrorCount();
        // this.comments = board.getComments();
    }

}

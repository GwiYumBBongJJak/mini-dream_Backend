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

    private Long likeCount;

    private Long dislikeCount;

    private Long horrorCount;

    List<Comment> comments;


    public BoardResponseDto(Board board, ReactionsDto reactionsDto) {
        this.boardId = board.getBoardId();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.nickname = board.getMember().getNickname();
        this.memberID = board.getMember().getMemberId();
        this.likeCount = reactionsDto.getLikeCount();
        this.dislikeCount = reactionsDto.getDislikeCount();
        this.horrorCount = reactionsDto.getHorrorCount();

//        this.likeCount = board.getLikeCount();
//        this.dislikeCount = board.getDislikeCount();
//        this.horrorCount = board.getHorrorCount();
        // this.comments = board.getComments();
    }
    public BoardResponseDto(Board board, List<Comment> commentList,ReactionsDto reactionsDto) {
        this.boardId = board.getBoardId();
        this.boardTitle = board.getBoardTitle();
        this.boardContent = board.getBoardContent();
        this.nickname = board.getMember().getNickname();
        this.memberID = board.getMember().getMemberId();
        this.comments = commentList;
        this.likeCount = reactionsDto.getLikeCount();
        this.dislikeCount = reactionsDto.getDislikeCount();
        this.horrorCount = reactionsDto.getHorrorCount();
//        this.likeCount = board.getLikeCount();
//        this.dislikeCount = board.getDislikeCount();
//        this.horrorCount = board.getHorrorCount();
        // this.comments = board.getComments();
    }

}

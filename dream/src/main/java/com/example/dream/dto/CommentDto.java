package com.example.dream.dto;

import com.example.dream.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDto {

    private String comment;
    private String nickname;
    private Board board;

}
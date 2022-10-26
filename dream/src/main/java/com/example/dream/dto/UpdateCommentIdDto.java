package com.example.dream.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateCommentIdDto {
    private String msg;

    private long commentId;
    private int statusCode;

    public UpdateCommentIdDto(String msg, long commentId, int statusCode) {
        this.msg = msg;
        this.commentId = commentId;
        this.statusCode = statusCode;
    }
}

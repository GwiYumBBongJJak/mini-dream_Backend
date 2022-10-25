package com.example.dream.dto;

import com.example.dream.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@NoArgsConstructor
public class BoardDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;

    private List<Comment> commentList;

    public BoardDto(String title, String content, List<Comment> commentList) {
        this.title = title;
        this.content = content;
//        this.commentList = commentList;
    }

    public BoardDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}


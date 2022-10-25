package com.example.dream.entity;

import com.example.dream.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @JoinColumn(name = "board_id")
    private Long boardId;

    @Column
    @JoinColumn(name = "comment_content")
    private String content;

    @ManyToOne
    private Member member;


    public void patch(CommentDto dto) {
        this.content = dto.getContent() != null ? dto.getContent() : this.content;
    }
}
package com.example.dream.entity;

import com.example.dream.dto.CommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @Column
    private String comment;

    //    @JsonIgnore
//    @ManyToOne
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JoinColumn(name = "board_id", nullable = false)
    @Column
    private Long boardId;

    @Column
    private String nickname;


    public Comment(CommentDto dto, Member member, Long boardId) {
        this.comment = dto.getComment();
        this.nickname = member.getNickname();
        this.boardId = boardId;
    }

    public void update(CommentDto dto,Member member) {
        this.comment = dto.getComment() != null ? dto.getComment() :this.comment;
        this.nickname = member.getNickname();

    }
}
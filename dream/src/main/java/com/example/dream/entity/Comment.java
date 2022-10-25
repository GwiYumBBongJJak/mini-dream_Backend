package com.example.dream.entity;

import com.example.dream.dto.CommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @Column
    private String comment_content;

//    @JsonIgnore
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column
    private String nickname;


    public Comment(CommentDto dto, Member member, Board board) {
        this.comment_content = dto.getComment_content();
        this.nickname = member.getNickname();
        this.board = board;
    }

    public void update(CommentDto dto,Member member, Board board) {
        this.comment_content = dto.getComment_content() != null ? dto.getComment_content() :this.comment_content;
        this.nickname = member.getNickname();
        this.board = board;
    }
}

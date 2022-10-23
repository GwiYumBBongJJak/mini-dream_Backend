package com.example.dream.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Comment extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @Column
    private Long board_id;

    @Column
    private String comment_content;

    @ManyToOne
    @JoinColumn(name="member")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board")
    private Board board;

}

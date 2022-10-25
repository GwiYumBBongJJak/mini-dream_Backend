package com.example.dream.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Dislikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dislike_id;

    @Column
    private Long boardId;

    @Column
    private Long memberId;

    public Dislikes(Long boardId, Long memberId) {
        this.boardId = boardId;
        this.memberId = memberId;
    }
}
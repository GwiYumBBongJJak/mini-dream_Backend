package com.example.dream.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long like_id;

    @Column
    private Long boardId;

    @Column
    private Long memberId;
//
//    @Column
//    private boolean dislike;
//
//    @Column
//    private boolean horror;


    public Likes(Long boardId, Long memberId) {
        this.boardId = boardId;
        this.memberId = memberId;
    }
}








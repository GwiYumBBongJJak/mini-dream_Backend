//package com.example.dream.entity;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@NoArgsConstructor
//public class Likes {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long likeId;
//    @ManyToOne
//    private Board board;
//
//    @Column
//    private Long memberId;
//
//    public Likes(Board board, Long memberId) {
//        this.board = board;
//        this.memberId = memberId;
//    }
//}


// use enum to express 3 different reactions.
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
//public class Dislikes {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long dislikeId;
//
//    @ManyToOne
//    private Board board;
//
//    @Column
//    private Long memberId;
//
//    public Dislikes(Board board, Long memberId) {
//        this.board = board;
//        this.memberId = memberId;
//    }
//}
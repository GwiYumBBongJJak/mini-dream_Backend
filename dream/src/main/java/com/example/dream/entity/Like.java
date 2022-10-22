package com.example.dream.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long like_id;

    @Column
    private Long board_id;

    @Column
    private Long member_id;
//
//    @Column
//    private boolean dislike;
//
//    @Column
//    private boolean horror;


}








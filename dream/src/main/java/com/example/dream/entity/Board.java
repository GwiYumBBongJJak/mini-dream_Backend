package com.example.dream.entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class Board extends Timestamp{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_id;

    @Column
    private String board_content;

    @Column
    private String board_title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column
    @OneToMany
    private List<Comment> comment;

}

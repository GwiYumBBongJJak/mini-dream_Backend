package com.example.dream.entity;


import com.example.dream.dto.BoardDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board extends Timestamp{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_id;

    @Column
    private String board_content;

    @Column
    private String board_title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

    @Column
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comment;

    public Board (BoardDto dto){
        this.board_content = dto.getBoard_content();
        this.board_title = dto.getBoard_title();
        this.member = dto.getMember();
    }


}

package com.example.dream.entity;

import com.example.dream.dto.ReactionDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Reactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reactionId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name ="boardId")
    private Board board;


    @Column
    private boolean liked;

    @Column
    private boolean disliked;

    @Column
    private boolean horrid;

    public Reactions(Member member, Board board, ReactionDto reactionDto) {
        this.member = member;
        this.board = board;
        this.liked = reactionDto.isLiked();
        this.disliked = reactionDto.isDisliked();
        this.horrid = reactionDto.isHorrid();
    }

    public void updateLike() {
        this.liked = !this.liked;

    }

    public void updateDislike() {
        this.disliked = !this.disliked;

    }
    public void updateHorrid() {
        this.horrid = !this.horrid;

    }

    public void update(ReactionDto dto, Member member, Board board) {
        this.member = member;
        this.board = board;
        this.liked = dto.isLiked();
        this.disliked = dto.isDisliked();
        this.horrid = dto.isHorrid();
    }


//    public void updateReactions(ReactionDto dto){
//        this.member = dto.get;
//        this.board = board;
//        this.liked = dto.isLiked() != this.liked ? dto.isLiked() : this.liked;
//        this.disliked = dto.isDisliked() != this.disliked ? dto.isDisliked() : this.liked;
//        this.horrid = dto.isHorrid() != this.horrid ? dto.isHorrid() : this.horrid;
//    }

}

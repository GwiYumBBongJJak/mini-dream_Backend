package com.example.dream.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ReactionDto {

    private  long memberId;

    private  long boardId;
    private boolean liked;
    private boolean disliked;
    private boolean horrid;



    public ReactionDto( boolean liked, boolean disliked, boolean horrid) {
//        this.memberId = getMemberId();
//        this.boardId = getBoardId();
        this.liked = liked;
        this.disliked = disliked;
        this.horrid = horrid;
    }






}

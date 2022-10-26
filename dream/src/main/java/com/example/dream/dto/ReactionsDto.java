package com.example.dream.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReactionsDto {

    private long likeCount;
    private long dislikeCount;
    private long horrorCount;

    @Builder
    public ReactionsDto(long likeCount, long dislikeCount, long horrorCount) {
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.horrorCount = horrorCount;
    }
}

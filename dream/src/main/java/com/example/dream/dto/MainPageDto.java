package com.example.dream.dto;


import com.example.dream.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MainPageDto {
// board title , like, dislike , horror,

    private List<Board> boards; // like count , member
//    private Long dislikeCount;
//    private Long horrorCount;



    public MainPageDto(List<Board> boards){
        this.boards = new ArrayList<>(boards);
    }



//    public MainPageDto(List<Board> boards, Long likeCount, Long dislikeCount, Long horrorCount, String nickname) {
//        this.boards = boards;
//        this.likeCount = likeCount;
//        this.dislikeCount = dislikeCount;
//        this.horrorCount = horrorCount;
//        this.nickname = nickname;
//    }
}

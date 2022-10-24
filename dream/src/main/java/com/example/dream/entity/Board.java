package com.example.dream.entity;


import com.example.dream.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
public class Board extends Timestamp{
//    @Formula("(select count(1) from comment bc where bc.board_id = id)")
//    private int totalCommentCount;
//
//    @Formula("(select count(1) from like bc where bc.board_id = id)")
//    private int totalLikeCount;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_id;

    @Column
    private String board_content;

    @Column
    private String board_title;

//    @Column
//    private String nickname;
//
//    @Column
//    private String username;


    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    // member 생성자?



    // 양방향 관계를 가급적이면 활용 안해야 한다. // why? n+1 문제가 발생 > 확인해라.

    @Column
    private Long like_count;


    public void boardUpdate(BoardDto dto) {
        this.board_title = dto.getTitle() != null ? dto.getTitle() : this.board_title;
        this.board_content = dto.getContent() != null ? dto.getContent() : this.board_title;
//        this.nickname = dto.getNickname() != null ? dto.getNickname() : this.nickname;
//        this.username = dto.getUsername() != null ? dto.getUsername() : this.username;
    }

    public boolean validateMember(Member member) {
        return !this.member.equals(member);
    }

}

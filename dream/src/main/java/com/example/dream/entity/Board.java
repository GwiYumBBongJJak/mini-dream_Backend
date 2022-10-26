package com.example.dream.entity;


import com.example.dream.dto.BoardDto;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Board extends Timestamp{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column
    private String boardContent;

    @Column
    private String boardTitle;

//    @Column
//    private String nickname;
//
//    @Column
//    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    // member 생성자?



    // 양방향 관계를 가급적이면 활용 안해야 한다. // why? n+1 문제가 발생 > 확인해라.



//    @Column
//    private int likeCount;
//
//    @Column
//    private int dislikeCount;
//
//    @Column
//    private int horrorCount;


    public void boardUpdate(BoardDto dto) {
        this.boardTitle = dto.getBoardTitle() != null ? dto.getBoardTitle() : this.boardTitle;
        this.boardContent = dto.getBoardContent() != null ? dto.getBoardContent() : this.boardContent;
//        this.nickname = dto.getNickname() != null ? dto.getNickname() : this.nickname;
//        this.username = dto.getUsername() != null ? dto.getUsername() : this.username;
    }

    public boolean validateMember(Member member) {
        return !this.member.equals(member);
    }

}

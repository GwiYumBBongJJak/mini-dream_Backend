package com.example.dream.entity;


import com.example.dream.dto.BoardDetailDto;
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

    @Column()
    private String boardContent;

    @Column
    private String boardTitle;


    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;


    public void boardUpdate(BoardDetailDto dto) {
        this.boardTitle = dto.getBoardTitle() != null ? dto.getBoardTitle() : this.boardTitle;
        this.boardContent = dto.getBoardContent() != null ? dto.getBoardContent() : this.boardContent;
        System.out.println("boardTitle = " + boardTitle);
        System.out.println("boardContent = " + boardContent);

    }

//    public boolean validateMember(Member member) {
//        return !this.member.equals(member);
//    }

}

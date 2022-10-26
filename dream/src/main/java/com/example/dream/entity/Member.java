package com.example.dream.entity;

import com.example.dream.dto.MemberRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
@Component

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column
    private String username;

    @Column
    private String nickname;

    @Column
    private String password;

    public Member (MemberRequestDto dto){
        this.username = dto.getUsername();
        this.nickname = dto.getNickname();
        this.password = dto.getPassword();
    }

}

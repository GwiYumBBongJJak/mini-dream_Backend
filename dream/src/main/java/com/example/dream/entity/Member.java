package com.example.dream.entity;

import com.example.dream.dto.MemberRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long member_id;

    @Column
    private String username;

    @Column
    private String nickname;

    @Column
    @JsonIgnore
    private String password;

//    public Member(String username, String nickname, String password){
//        this.username = username;
//        this.nickname = nickname;
//        this.password = password;
//    }

    public Member (MemberRequestDto dto){
        this.username = dto.getUsername();
        this.nickname = dto.getNickname();
        this.password = dto.getPassword();
    }

}

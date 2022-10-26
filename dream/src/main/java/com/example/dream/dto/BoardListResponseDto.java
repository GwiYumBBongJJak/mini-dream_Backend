package com.example.dream.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class BoardListResponseDto {

    List<BoardResponseDto> boardResponseDtos = new LinkedList<>();



    public void addBoard(BoardResponseDto boardResponseDto){
        boardResponseDtos.add(boardResponseDto);
    }
}

package com.example.dream.service;


import com.example.dream.dto.BoardDto;
import com.example.dream.dto.BoardListResponseDto;
import com.example.dream.dto.BoardResponseDto;
import com.example.dream.dto.GlobalResDto;
import com.example.dream.entity.Board;
import com.example.dream.entity.Member;
import com.example.dream.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;


    @Transactional
    public GlobalResDto saveBoard(BoardDto boardRequestDto, Member member) {

//        if (null == member) {
//            return new GlobalResDto("INVALID_TOKEN", HttpStatus.FORBIDDEN.value());
//        }

        Board board = Board.builder()
//                .username(boardRequestDto.getUsername())
//                .nickname(boardRequestDto.getNickname())
                .board_content(boardRequestDto.getContent())
                .board_title(boardRequestDto.getTitle())
                .member(member)
                .build();

        boardRepository.save(board);

        return new GlobalResDto("Success Save Course", HttpStatus.OK.value());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<BoardResponseDto> getBoard(Long id) {

        Board board = checkBoard(boardRepository, id);

        return ResponseEntity.ok(new BoardResponseDto(board));
    }

    @Transactional(readOnly = true)
    public BoardListResponseDto getBoards() {

        BoardListResponseDto boardListResponseDto = new BoardListResponseDto();
        List<Board> boards = boardRepository.findAll();

        for (Board board : boards) {
            boardListResponseDto.addBoard(new BoardResponseDto(board));
        }

        return boardListResponseDto;
    }

    @Transactional
    public GlobalResDto updateBoard(Long id, BoardDto boardRequestDto) {


        Board board = checkBoard(boardRepository, id);
        board.boardUpdate(boardRequestDto);

        return new GlobalResDto("Success Update Course", HttpStatus.OK.value());

    }

    @Transactional
    public GlobalResDto deleteBoard(Long id) {
        Board board = checkBoard(boardRepository, id);
        boardRepository.delete(board);
        return new GlobalResDto("Success Delete Course", HttpStatus.OK.value());
    }

    private Board checkBoard(BoardRepository boardRepository, Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not Found Course")
        );
    }

    @Transactional(readOnly = true)
    public Board isPresentPost(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        return optionalBoard.orElse(null);
    }


}


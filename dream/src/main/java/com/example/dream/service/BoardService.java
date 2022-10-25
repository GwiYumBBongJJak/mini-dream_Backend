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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    // private final CommentRepository commentRepository;
//    private final JwtUtil jwtUtil;


    @Transactional
    public GlobalResDto saveBoard(BoardDto boardRequestDto, Member member) {

//        if (null == member) {
//            return new GlobalResDto("INVALID_TOKEN", HttpStatus.FORBIDDEN.value());
//        }

        Board board = Board.builder()
//                .username(boardRequestDto.getUsername())
//                .nickname(boardRequestDto.getNickname())
                .boardContent(boardRequestDto.getBoardContent())
                .boardTitle(boardRequestDto.getBoardTitle())
                .member(member)
                .build();

        boardRepository.save(board);

        return new GlobalResDto("게시글 작성이 완료되었습니다.", HttpStatus.OK.value());
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
    public GlobalResDto updateBoard(Long id, BoardDto boardRequestDto, Member member) {

        Board board = checkBoard(boardRepository, id);

        if (!board.getMember().getUsername().equals(member.getUsername())) {
            throw new RuntimeException("작성자만 수정이 가능합니다.");
        }

        board.boardUpdate(boardRequestDto);

        return new GlobalResDto("게시글 수정이 완료되었습니다.", HttpStatus.OK.value());

    }

    @Transactional
    public GlobalResDto deleteBoard(Long id, Member member) {

        Board board = checkBoard(boardRepository, id);

        if (!board.getMember().getUsername().equals(member.getUsername())) {
            throw new RuntimeException("작성자만 삭제가 가능합니다.");
        }

        boardRepository.delete(board);

        return new GlobalResDto("게시글 삭제가 완료되었습니다.", HttpStatus.OK.value());
    }

    private Board checkBoard(BoardRepository boardRepository, Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("게시글이 존재하지 않습니다.")
        );
    }

    @Transactional(readOnly = true)
    public Board isPresentPost(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        return optionalBoard.orElse(null);
    }


}


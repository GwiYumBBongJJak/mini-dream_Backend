package com.example.dream.service;


import com.example.dream.dto.*;
import com.example.dream.entity.Board;
import com.example.dream.entity.Comment;
import com.example.dream.entity.Member;
import com.example.dream.repository.BoardRepository;
import com.example.dream.repository.CommentRepository;
import com.example.dream.repository.ReactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
     private final CommentRepository commentRepository;

     private final ReactionsRepository reactionsRepository;


    @Transactional
    public GlobalResDto saveBoard(BoardDto boardRequestDto, Member member) {

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

    // board title , memeber nickname, like count, dislike count, horror count

        List<Comment> commentList = commentRepository.findCommentByBoardId(id);
        Board board = checkBoard(boardRepository, id);

        long likeCount = reactionsRepository.countReactionsByBoard_BoardIdAndLikedTrue(id);
        long dislikeCount = reactionsRepository.countReactionsByBoard_BoardIdAndDislikedTrue(id);
        long horrorCount = reactionsRepository.countReactionsByBoard_BoardIdAndHorridTrue(id);

        ReactionsDto dto = new ReactionsDto(likeCount,dislikeCount,horrorCount);

//        BoardResponseDto boardDto = new BoardResponseDto(board, commentList,dto);

        return ResponseEntity.ok(new BoardResponseDto(board,commentList,dto));
    }

    @Transactional(readOnly = true)
    public BoardListResponseDto getBoards() {


        BoardListResponseDto boardListResponseDto = new BoardListResponseDto();
        List<Board> boards = boardRepository.findAll();

        for (Board board : boards) {
            long likeCount = reactionsRepository.countReactionsByBoard_BoardIdAndLikedTrue(board.getBoardId());
            long dislikeCount = reactionsRepository.countReactionsByBoard_BoardIdAndDislikedTrue(board.getBoardId());
            long horrorCount = reactionsRepository.countReactionsByBoard_BoardIdAndHorridTrue(board.getBoardId());
            ReactionsDto dto = new ReactionsDto(likeCount,dislikeCount,horrorCount);
            boardListResponseDto.addBoard(new BoardResponseDto(board,dto));

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
}


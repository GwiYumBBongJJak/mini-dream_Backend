package com.example.dream.service;

import com.example.dream.dto.CommentDto;
import com.example.dream.dto.GlobalResDto;
import com.example.dream.entity.Board;
import com.example.dream.entity.Comment;
import com.example.dream.entity.Member;
import com.example.dream.repository.BoardRepository;
import com.example.dream.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;

    private final CommentRepository commentRepository;

    @Transactional
    public GlobalResDto create(Long boardId, CommentDto dto, Member member) {
        //게시글 조회및 예외 발생
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패 대상 게시글이 없습니다."));

        //댓글 엔티티 생성
        Comment comment = Comment.builder()
                .boardId(board.getBoard_id())
                .content(dto.getContent())
                .member(member)
                .build();

        //댓글 엔티티를 db로 저장
        commentRepository.save(comment);

        return new GlobalResDto("Success Save Course", HttpStatus.OK.value());
    }

    public GlobalResDto update(Long id, CommentDto dto) {
        //댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("댓글 수정 실패 대상 댓글이 없습니다."));
        //댓글 수정
        target.patch(dto);
        //db로 갱신
        commentRepository.save(target);
        //댓글 엔티티를 DTO로 변환 및 반환
        return new GlobalResDto("Success Save Course", HttpStatus.OK.value());

    }

    public GlobalResDto delete(Long id) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("댓글 삭제 실패 대상이 없습니다."));
        //댓글 db삭제
        commentRepository.delete(target);

        return new GlobalResDto("Success delete Course", HttpStatus.OK.value());
    }
}
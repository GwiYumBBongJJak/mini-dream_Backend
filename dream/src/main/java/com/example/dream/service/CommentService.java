package com.example.dream.service;

import com.example.dream.dto.CommentDto;
import com.example.dream.entity.Board;
import com.example.dream.entity.Comment;
import com.example.dream.entity.Member;
import com.example.dream.repository.BoardRepository;
import com.example.dream.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final BoardRepository boardRepository;

    @Transactional
    public ResponseEntity<?> createComment(CommentDto dto,Long boardId, Member member ) {;
        Board board = boardRepository.findById(boardId).orElseThrow();
        Comment comment = new Comment(dto, member,board);
        commentRepository.save(comment);
        return new ResponseEntity<>(comment,HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<?> updateComment(CommentDto dto, Long commentId, Member member){
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new IllegalArgumentException("invalid input"));
        comment.update(dto,member,comment.getBoard());
        return new ResponseEntity<>(commentRepository.findById(commentId),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
        return new ResponseEntity<>("댓글 삭제 완료!",HttpStatus.OK);
    }
}

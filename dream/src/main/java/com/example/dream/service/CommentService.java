package com.example.dream.service;

import com.example.dream.dto.CommentDto;
import com.example.dream.dto.LoginResponseDto;
import com.example.dream.entity.Board;
import com.example.dream.entity.Comment;
import com.example.dream.entity.Member;
import com.example.dream.repository.BoardRepository;
import com.example.dream.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final BoardRepository boardRepository;

    @Transactional
    public ResponseEntity<?> createComment(CommentDto dto, Long boardId, Member member) {
        ;
        Board board = boardRepository.findById(boardId).orElseThrow();
        Comment comment = new Comment(dto, member, boardId);
        commentRepository.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    public ResponseEntity<?> isUpdate(Long commentId, Member member) {
        Optional<Comment> optional = commentRepository.findById(commentId);
        Comment comment = optional.orElseThrow(() -> new IllegalArgumentException("no"));
        if (!comment.getNickname().equals(member.getNickname())) {
            LoginResponseDto response = new LoginResponseDto("작성자가 다릅니다", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            LoginResponseDto response = new LoginResponseDto("수정이 가능한 댓글입니다", HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @Transactional
    public ResponseEntity<?> updateComment(CommentDto dto, Long commentId, Member member) {
        Optional<Comment> optional = commentRepository.findById(commentId);
        Comment comment = optional.orElseThrow(() -> new IllegalArgumentException("no"));
        comment.update(dto, member);
        return new ResponseEntity<>(commentRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> isDelete(Long commentId, Member member) {
        Optional<Comment> optional = commentRepository.findById(commentId);
        Comment comment = optional.orElseThrow(() -> new IllegalArgumentException("no"));
        if (!comment.getNickname().equals(member.getNickname())) {
            LoginResponseDto response = new LoginResponseDto("작성자가 다릅니다", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            LoginResponseDto response = new LoginResponseDto("삭제가 가능한 댓글입니다", HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }




    //2중 logic. 한번 확인하는 절차?
    @Transactional
    public ResponseEntity<?> deleteComment(Long commentId, Member member) {
        Optional<Comment> optional = commentRepository.findById(commentId);
        Comment comment = optional.orElseThrow(() -> new IllegalArgumentException("no"));
        if (!comment.getNickname().equals(member.getNickname())) {
            LoginResponseDto response = new LoginResponseDto("작성자가 다릅니다", HttpStatus.BAD_REQUEST.value());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            commentRepository.deleteById(commentId);
            LoginResponseDto response = new LoginResponseDto("삭제가 완료 되었습니다", HttpStatus.OK.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }


//        Optional<Comment> optional = commentRepository.findById(commentId);
//        Comment comment = optional.orElseThrow(() -> new IllegalArgumentException("no"));
//        commentRepository.deleteById(commentId);
//        LoginResponseDto response = new LoginResponseDto("삭제가 완료 되었습니다", HttpStatus.OK.value());
//        return new ResponseEntity<>(response, HttpStatus.OK);
        }


        public ResponseEntity<?> getComments (Member member){

            return new ResponseEntity<>(commentRepository.findAll(), HttpStatus.OK);

        }



}

package com.example.dream.controller;

import com.example.dream.dto.BoardDto;
import com.example.dream.dto.CommentDto;
import com.example.dream.dto.GlobalResDto;
import com.example.dream.service.BoardService;
import com.example.dream.service.CommentService;
import com.example.dream.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/api/board/{boardId}/comment")
    public GlobalResDto createBoard(@PathVariable Long boardId ,@RequestBody CommentDto dto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.create(boardId,dto, userDetails.getMember());
    }

    //댓글 수정
    @PatchMapping("/api/comment/{id}")
    public GlobalResDto update(@PathVariable Long id, @RequestBody CommentDto dto) {
        return commentService.update(id,dto);
    }

    //댓글 삭제
    @DeleteMapping("/api/comment/{id}")
    public GlobalResDto delete(@PathVariable Long id) {
        return commentService.delete(id);
    }
//2

}

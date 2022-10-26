package com.example.dream.controller;

import com.example.dream.dto.CommentDto;
import com.example.dream.dto.LoginResponseDto;
import com.example.dream.service.CommentService;
import com.example.dream.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping(value= "/{boardId}/create", consumes = "application/json")
    public ResponseEntity<?> createComment(@PathVariable Long boardId , @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentDto dto){
        return commentService.createComment(dto,boardId,userDetails.getMember());
    }

    @PutMapping(value ="/{commentId}/update", consumes = "application/json")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentDto dto){
        return commentService.updateComment(dto,commentId,userDetails.getMember());
    }

    @PostMapping("/{commentId}/isUpdate")// 이거를 하면 수정 완료 버튼 active 해주기
    public ResponseEntity<?> isUpdate(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails.getMember()==null){
            LoginResponseDto dto = new LoginResponseDto("로그인을 해주세요", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(dto,HttpStatus.BAD_REQUEST);
        }
        return commentService.isUpdate(commentId, userDetails.getMember());
    }

    @DeleteMapping("/{commentId}/delete")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(commentId, userDetails.getMember());
    }

//    @GetMapping("/{commentId}/isDelete")// 이거를 하면 삭제 완료 버튼 active 해주기
//    public ResponseEntity<?> isDelete(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
//        return commentService.isDelete(commentId, userDetails.getMember());
//    }

    @GetMapping
    public ResponseEntity<?> getComments(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.getComments(userDetails.getMember());
    }
}
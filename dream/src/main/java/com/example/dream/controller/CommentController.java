package com.example.dream.controller;


import com.example.dream.dto.CommentDto;
import com.example.dream.service.CommentService;
import com.example.dream.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
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

    @DeleteMapping("/{commentId}/delete")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId){
        return commentService.deleteComment(commentId);
    }


}

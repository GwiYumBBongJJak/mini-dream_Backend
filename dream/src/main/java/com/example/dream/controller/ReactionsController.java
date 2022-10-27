package com.example.dream.controller;

import com.example.dream.service.ReactionsService;
import com.example.dream.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReactionsController {
    private final ReactionsService reactionsService;

    @PostMapping("/auth/{boardId}/{action}")
    public ResponseEntity<?> reaction(@PathVariable Long boardId,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable String action){
        System.out.println("boardId = " + boardId);
        System.out.println("action = " + action);
        System.out.println("userDetails = " + userDetails.getMember());
        return reactionsService.reaction(boardId,userDetails.getMember(),action);
    }
}

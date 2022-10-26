package com.example.dream.controller;

import com.example.dream.service.ReactionsService;
import com.example.dream.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class ReactionsController {
    private final ReactionsService reactionsService;

    @PostMapping("/{boardId}/{action}/")
    public ResponseEntity<?> reaction(@PathVariable Long boardId,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable String action){



        return reactionsService.reaction(boardId,userDetails.getMember(),action);
    }
//    @PostMapping("/{boardId}/reaction/dislike")
//    public ResponseEntity<?> dislike(@PathVariable Long boardId,
//                                   @AuthenticationPrincipal UserDetailsImpl userDetails){
//        return reactionsService.dislike(boardId,userDetails.getMember().getMemberId());
//    }
//    @PostMapping("/{boardId}/reaction/horror")
//    public ResponseEntity<?> horror(@PathVariable Long boardId,
//                                   @AuthenticationPrincipal UserDetailsImpl userDetails){
//        return reactionsService.horror(boardId,userDetails.getMember().getMemberId());
//    }


}

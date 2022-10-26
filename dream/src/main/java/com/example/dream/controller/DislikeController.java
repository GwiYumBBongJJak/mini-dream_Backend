//package com.example.dream.controller;
//
//import com.example.dream.service.DislikeService;
//import com.example.dream.service.UserDetailsImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class DislikeController {
//
//    private final DislikeService dislikeService;
//    @PostMapping("/{board_id}/dislike")
//    public ResponseEntity<?> dislikes(@PathVariable Long board_id,
//                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
//        return dislikeService.dislikes(board_id,userDetails.getMember().getMember_id());
//    }
//}
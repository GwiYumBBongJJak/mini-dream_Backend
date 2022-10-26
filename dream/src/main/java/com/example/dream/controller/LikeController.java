//package com.example.dream.controller;
//
//import com.example.dream.service.LikeService;
//import com.example.dream.service.UserDetailsImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/auth")
//@RequiredArgsConstructor
//public class LikeController {
//    private final LikeService likeService;
//
//    @PostMapping("/{board_id}/like")
//    public ResponseEntity<?> likes(@PathVariable Long board_id,
//                                   @AuthenticationPrincipal UserDetailsImpl userDetails){
//        return likeService.likes(board_id,userDetails.getMember().getMember_id());
//    }
//}

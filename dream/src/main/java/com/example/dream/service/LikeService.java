//package com.example.dream.service;
//
//import com.example.dream.entity.Like;
//import com.example.dream.repository.BoardRepository;
//import com.example.dream.repository.LikeRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class LikeService {
//
//    private final BoardRepository boardRepository;
//
//    private final LikeRepository likeRepository;
//    public ResponseEntity<?> like(Long board_id, Long member_id) {
//        Optional <Like> like = likeRepository.findByBoard_id(board_id);
//        if(like.isPresent()){
//            likeRepository.deleteLikeByBoard_id(board_id);
//        }
//        Like saveLike = new Like(board_id,member_id);
//
//        return new ResponseEntity<>(likeRepository.save(saveLike), HttpStatus.CREATED);
//
//    }
//}

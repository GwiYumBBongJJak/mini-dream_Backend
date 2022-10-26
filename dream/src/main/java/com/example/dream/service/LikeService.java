//package com.example.dream.service;
//
//import com.example.dream.entity.Board;
//import com.example.dream.entity.Likes;
//import com.example.dream.repository.BoardRepository;
//import com.example.dream.repository.LikesRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class LikeService {
//
//    private final BoardRepository boardRepository;
//
//
//    private final LikesRepository likesRepository;
//    @Transactional
//    public ResponseEntity<?> likes(Long board_id, Long member_id) {
//        Optional<Likes> like = likesRepository.findLikesBymemberId(member_id);
//        if(like.isPresent()){
//            System.out.println("it is liked");
//            Likes likeDelete = like.orElseThrow(()->new IllegalArgumentException("dc"));
//            likesRepository.delete(likeDelete);
//            return new ResponseEntity<>("좋아요 취소",HttpStatus.OK);
//        }else{
//            System.out.println("like 해");
//            Board board = boardRepository.findBoardsByBoardId(board_id);
//
//
//            Likes saveLike = new Likes(board,member_id);
//
//            return new ResponseEntity<>(likesRepository.save(saveLike), HttpStatus.CREATED);
//
//        }
//
//    }
//}
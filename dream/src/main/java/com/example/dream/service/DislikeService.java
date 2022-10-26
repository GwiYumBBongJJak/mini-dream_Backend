//package com.example.dream.service;
//
//import com.example.dream.entity.Board;
//import com.example.dream.entity.Dislikes;
//import com.example.dream.repository.BoardRepository;
//import com.example.dream.repository.DislikesRepository;
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
//public class DislikeService {
//    private final DislikesRepository dislikeRepository;
//    private final BoardRepository boardRepository;
//
//    @Transactional
//    public ResponseEntity<?> dislikes(Long board_id, Long member_id) {
//        Optional<Dislikes> dislike = dislikeRepository.findDislikesBymemberId(member_id);
//        if(dislike.isPresent()){
//            System.out.println("it is disliked");
//            Dislikes dislikeDelete = dislike.orElseThrow(()->new IllegalArgumentException("dc"));
//            dislikeRepository.delete(dislikeDelete);
//            return new ResponseEntity<>("싫어요 취소", HttpStatus.OK);
//        }else{
//            System.out.println("like 해");
//            Board board = boardRepository.findBoardsByBoardId(board_id);
//
//
//            Dislikes saveDislike = new Dislikes(board,member_id);
//
//            return new ResponseEntity<>(dislikeRepository.save(saveDislike), HttpStatus.CREATED);
//
//        }
//
//    }
//}
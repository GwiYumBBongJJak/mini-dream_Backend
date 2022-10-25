//package com.example.dream.repository;
//
//import com.example.dream.entity.Like;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface LikeRepository extends JpaRepository<Like,Long> {
//    Optional<Like> findByBoard_id(Long board_id);
//    Optional<Like> deleteLikeByBoard_id(Long board_id);
//}

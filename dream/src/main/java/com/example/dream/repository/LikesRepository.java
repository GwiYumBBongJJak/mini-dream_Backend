package com.example.dream.repository;

import com.example.dream.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes,Long> {
    Optional<Likes> findLikesBymemberId(Long board_id);
    Optional<Likes> deleteLikesByboardId(Long board_id);


}

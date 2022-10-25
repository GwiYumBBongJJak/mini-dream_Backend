package com.example.dream.repository;

import com.example.dream.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes,Long> {
    Optional<Likes> findLikesBymemberId(Long member_id);



    List<Likes> findLikesByboardId(Long board_id);
}

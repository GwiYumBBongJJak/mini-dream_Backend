package com.example.dream.repository;

import com.example.dream.entity.Dislikes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DislikesRepository extends JpaRepository<Dislikes,Long> {

    Optional<Dislikes> findDislikesBymemberId(Long member_id);



    List<Dislikes> findDislikesByboardId(Long board_id);
}
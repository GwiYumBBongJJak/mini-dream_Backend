package com.example.dream.repository;

import com.example.dream.entity.Reactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReactionsRepository extends JpaRepository<Reactions,Long> {
    Optional<Reactions> findReactionByMemberMemberIdAndBoard_BoardId(Long memberId, Long boardId);
    long countReactionsByBoard_BoardIdAndLikedTrue(Long boardId);

    Reactions findReactionsByMemberMemberId(Long memberId);

    long countReactionsByBoard_BoardIdAndHorridTrue(Long boardId);

    long countReactionsByBoard_BoardIdAndDislikedTrue(Long boardId);
//    List<Likes> findLikesByboardId(Long board_id);
}
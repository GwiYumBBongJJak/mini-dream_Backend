package com.example.dream.service;

import com.example.dream.dto.ReactionDto;
import com.example.dream.dto.ReactionsDto;
import com.example.dream.entity.Board;
import com.example.dream.entity.Member;
import com.example.dream.entity.Reactions;
import com.example.dream.repository.BoardRepository;
import com.example.dream.repository.ReactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReactionsService {

    private final BoardRepository boardRepository;

    private final ReactionsRepository reactionsRepository;

    @Transactional
    public ResponseEntity<?> reaction(Long boardId, Member member, String action) {
        Board board = boardRepository.findBoardsByBoardId(boardId);
        System.out.println("board = " + board);
        Optional<Reactions> checking = reactionsRepository.findReactionByMemberMemberIdAndBoard_BoardId(member.getMemberId(),boardId);
        System.out.println("checking = " + checking);
        
        if (checking.isEmpty()){
            // 멤버 아이디로 조회한 리액션 결과

            if(action.equals("LIKE")){

                ReactionDto dto = new ReactionDto(true, false, false);
                Reactions saveLike = new Reactions(member, board, dto);
                reactionsRepository.save(saveLike);
            }

            if(action.equals("DISLIKE")){
                ReactionDto dto = new ReactionDto(false, true, false);
                Reactions saveLike = new Reactions(member, board, dto);
                reactionsRepository.save(saveLike);
            }

            if(action.equals("HORROR")){
                ReactionDto dto = new ReactionDto(false, false, true);
                Reactions saveLike = new Reactions(member, board, dto);
                reactionsRepository.save(saveLike);
            }
        }
        else{
            Reactions checkedReaction = checking.orElseThrow();
            //멤버 아이디로 조회한 결과가 있을경우.

            if(action.equals("LIKE")){
                ReactionDto dto = new ReactionDto(!checkedReaction.isLiked(),checkedReaction.isDisliked(),checkedReaction.isHorrid());

                checkedReaction.update(dto,member, board);
            }

            if(action.equals("DISLIKE")){
                ReactionDto dto = new ReactionDto(checkedReaction.isLiked(),!checkedReaction.isDisliked(),checkedReaction.isHorrid());
                System.out.println(dto + " when DISLIKE is pressed. Not virgin");
                checkedReaction.update(dto,member, board);
            }
            if(action.equals("HORROR")){
                ReactionDto dto = new ReactionDto(checkedReaction.isLiked(),checkedReaction.isDisliked(),!checkedReaction.isHorrid());
                System.out.println(dto + " when HORROR is pressed. Not virgin");
                checkedReaction.update(dto,member, board);
            }

        }
        long likeCount =  reactionsRepository.countReactionsByBoard_BoardIdAndLikedTrue(boardId);
        long dislikeCount = reactionsRepository.countReactionsByBoard_BoardIdAndDislikedTrue(boardId);
        long horrorCount = reactionsRepository.countReactionsByBoard_BoardIdAndHorridTrue(boardId);
        ReactionsDto dto = ReactionsDto.builder()
                .boardId(boardId)
                .likeCount(likeCount)
                .dislikeCount(dislikeCount)
                .horrorCount(horrorCount)
                .build();

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
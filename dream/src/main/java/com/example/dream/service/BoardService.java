package com.example.dream.service;

import com.example.dream.dto.BoardDto;
import com.example.dream.entity.Board;
import com.example.dream.entity.Member;
import com.example.dream.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public ResponseEntity<?> getBoards(){
        return new ResponseEntity<>(boardRepository.findAll(), HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity<?> createBoard(UserDetailsImpl userDetails, BoardDto dto, Member member) {


       dto.setMember(userDetails.getMember());
        Board board = new Board(dto);

        return new ResponseEntity<>(boardRepository.save(board), HttpStatus.OK);
    }

    public ResponseEntity<?> getBoard(Long id) {
        return new ResponseEntity<>(boardRepository.findById(id),HttpStatus.OK);
    }
}

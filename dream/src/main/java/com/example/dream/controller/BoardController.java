package com.example.dream.controller;


import com.example.dream.dto.BoardDto;
import com.example.dream.entity.Member;
import com.example.dream.service.BoardService;
import com.example.dream.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board")
    public ResponseEntity<?> getBoards(){
        return boardService.getBoards();
    }

    @PostMapping("/board/create")
    public ResponseEntity<?> createBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody BoardDto dto, Member member){
        System.out.println(userDetails.getMember().getNickname());

        return boardService.createBoard(userDetails,dto,member);
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id){
        return boardService.getBoard(id);
    }

}

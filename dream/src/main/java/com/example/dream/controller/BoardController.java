package com.example.dream.controller;

import com.example.dream.dto.BoardDetailDto;
import com.example.dream.dto.BoardListResponseDto;
import com.example.dream.dto.BoardResponseDto;
import com.example.dream.dto.Response.GlobalResDto;
import com.example.dream.service.BoardService;
import com.example.dream.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    // 전체 게시글 보기
    @GetMapping("/boards")
    public BoardListResponseDto getBoards() {
        return boardService.getBoards();
    }

    // 게시글 등록
    // @AuthenticationPrincipal 활용 장점 : Jwt 필터에서 검증된걸 가져온다, 기존 내 방식은 2중 검증이다 어떻게 보면.
    @PostMapping("/auth/boards/create")
    public GlobalResDto createBoard(@RequestBody BoardDetailDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println(userDetails);
        return boardService.saveBoard(requestDto, userDetails.getMember());
    }

    // 게시글 조회
    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id) {
        System.out.println("겟 보드 하나만 보여주기");
        return boardService.getBoard(id);
    }

    //게시글 수정
    @PutMapping("/auth/boards/modify/{id}")
    public GlobalResDto updateBoard(@PathVariable Long id, @RequestBody BoardDetailDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.updateBoard(id, requestDto, userDetails.getMember());
    }

    //게시글 삭제
    @DeleteMapping("/auth/boards/delete/{id}")
    public GlobalResDto deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.deleteBoard(id, userDetails.getMember());
    }
}
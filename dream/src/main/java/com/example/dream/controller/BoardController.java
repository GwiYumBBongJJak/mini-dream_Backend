package com.example.dream.controller;

import com.example.dream.dto.BoardDto;
import com.example.dream.dto.BoardListResponseDto;
import com.example.dream.dto.BoardResponseDto;
import com.example.dream.dto.GlobalResDto;
import com.example.dream.service.BoardService;
import com.example.dream.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class BoardController {
    private final BoardService boardService;

    // 전체 게시글 보기
    @GetMapping("/boards")
    public BoardListResponseDto getBoards(){
        return boardService.getBoards();
    }




    // 게시글 등록
    // @AuthenticationPrincipal 활용 장점 : Jwt 필터에서 검증된걸 가져온다, 기존 내 방식은 2중 검증이다 어떻게 보면.
    @PostMapping("/boards/post")
    public GlobalResDto createBoard(@RequestBody BoardDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(userDetails);
        return boardService.saveBoard(requestDto, userDetails.getMember());
    }

    // 게시글 조회
    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }





    //게시글 수정
    @PutMapping("/boards/{id}")
    public GlobalResDto updateBoard(@PathVariable Long id, @RequestBody BoardDto requestDto) {
        return boardService.updateBoard(id, requestDto);
    }

    //게시글 삭제
    @DeleteMapping("/boards/{id}")
    public GlobalResDto deleteBoard(@PathVariable Long id) {
        return boardService.deleteBoard(id);
    }
}


package com.example.dream.controller;

import com.example.dream.Jwt.JwtUtil;
import com.example.dream.dto.LoginDto;
import com.example.dream.dto.LoginResponseDto;
import com.example.dream.dto.MemberRequestDto;
import com.example.dream.entity.Member;
import com.example.dream.service.MemberService;
import com.example.dream.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public LoginResponseDto signup(@RequestBody @Valid MemberRequestDto requestDto) {
        return memberService.signup(requestDto);
    }
    @GetMapping("/id/check")
    public ResponseEntity<?> idCheck(@RequestBody Member member){
        return memberService.idCheck(member.getUsername());
    }

    @GetMapping("/nickname/check")
    public ResponseEntity<?> nicknameCheck(@RequestBody Member member){
        return memberService.nicknameCheck(member.getNickname());
    }

    @PostMapping("/dummy/create")
    public LoginResponseDto dummyShow( @RequestBody @Valid MemberRequestDto requestDto) {
        return memberService.signup(requestDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody @Valid LoginDto dto, HttpServletResponse response) {
        return memberService.login(dto, response);
    }

    @GetMapping("/issue/token")
    public LoginResponseDto issuedToken(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response){
        response.addHeader(JwtUtil.ACCESS_TOKEN,jwtUtil.createToken(userDetails.getMember().getUsername(),"Access"));
        return new LoginResponseDto("Issue Success", HttpStatus.OK.value());
    }
}

package com.example.dream.controller;

import com.example.dream.Jwt.JwtUtil;
import com.example.dream.dto.LoginDto;
import com.example.dream.dto.LoginResponseDto;
import com.example.dream.dto.MemberRequestDto;
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
@RequestMapping("")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @PostMapping(value = "/member/register", consumes = "application/json")
    public ResponseEntity<?> signup(@RequestBody @Valid MemberRequestDto requestDto ) {
        return memberService.signup(requestDto);
    }

    @PostMapping(value = "/member/login", consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto dto, HttpServletResponse response) {
        return memberService.login(dto, response);
    }

    @PostMapping(value = "/member/id/{username}")
    public ResponseEntity<?> checkUsername(@PathVariable String username){
        System.out.println(username);
        return memberService.checkUsername(username);
    }

    @PostMapping(value = "/member/nickname/{nickname}")
    public ResponseEntity<?> checkNickname(@PathVariable String nickname){
        return memberService.checkNickname(nickname);
    }

    @GetMapping("/issue/token")
    public LoginResponseDto issuedToken(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response){
        response.addHeader(JwtUtil.ACCESS_TOKEN,jwtUtil.createToken(userDetails.getMember().getUsername(),"Access"));
        return new LoginResponseDto("Issue Success", HttpStatus.OK.value());
    }
    @GetMapping("/auth/member/info")
    public LoginResponseDto getNickname(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return memberService.getNickname(userDetails);
    }
}
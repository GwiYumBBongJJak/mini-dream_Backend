package com.example.dream.service;


import com.example.dream.Jwt.JwtUtil;
import com.example.dream.dto.LoginDto;
import com.example.dream.dto.LoginResponseDto;
import com.example.dream.dto.MemberRequestDto;
import com.example.dream.dto.TokenDto;
import com.example.dream.entity.Member;
import com.example.dream.entity.RefreshToken;
import com.example.dream.repository.MemberRepository;
import com.example.dream.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public LoginResponseDto signup(MemberRequestDto requestDto) {
        //username duplication check
//        if (memberRepository.findByUsername(requestDto.getUsername()).isPresent()) {
//            throw new RuntimeException("duplication in username");
//        };

        requestDto.setPasswordEncoder(passwordEncoder.encode(requestDto.getPassword()));
        Member member = new Member(requestDto);

        memberRepository.save(member);
        return new LoginResponseDto("회원가입에 성공하셨습니다!", HttpStatus.OK.value());

    }

    public LoginResponseDto login(LoginDto loginDto, HttpServletResponse response) {
        System.out.println(loginDto.getUsername());
        Member member = memberRepository.findByUsername(loginDto.getUsername()).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
            throw new RuntimeException("비밀번호가 일치 하지 않습니다");
        }
        TokenDto tokenDto = jwtUtil.createAllToken(loginDto.getUsername());
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByMemberUsername(loginDto.getUsername());
        if (refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().update(tokenDto.getRefreshToken()));
        } else {
            RefreshToken newRefreshToken = new RefreshToken(tokenDto.getRefreshToken(), loginDto.getUsername());
            refreshTokenRepository.save(newRefreshToken);
        }
        setHeader(response, tokenDto);

        return new LoginResponseDto("로그인 완료!", HttpStatus.OK.value());
    }
    private void setHeader(HttpServletResponse response, TokenDto tokenDto){
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }

    public ResponseEntity<?> idCheck(String username) {

        Optional<Member> member = memberRepository.findByUsername(username);
        if(member.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(username, HttpStatus.OK);
    }

    public ResponseEntity<?> nicknameCheck(String nickname) {

        Optional<Member> member = memberRepository.findByNickname(nickname);
        if(member.isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(nickname, HttpStatus.OK);
    }

}

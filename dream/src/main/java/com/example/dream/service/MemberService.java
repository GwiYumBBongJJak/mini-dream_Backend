package com.example.dream.service;


import com.example.dream.Jwt.JwtUtil;
import com.example.dream.dto.*;
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
    public ResponseEntity<?> signup(MemberRequestDto requestDto) {

        requestDto.setPasswordEncoder(passwordEncoder.encode(requestDto.getPassword()));
        Member member = new Member(requestDto);

        memberRepository.save(member);
        LoginResponseDto loginDto = new LoginResponseDto("회원가입이 완료 되었습니다", HttpStatus.OK.value());
        return new ResponseEntity<>(loginDto,HttpStatus.OK);

    }

    public ResponseEntity<?> login(LoginDto loginDto, HttpServletResponse response) {

        Optional<Member> member = memberRepository.findByUsername(loginDto.getUsername());
        if(member.isEmpty()){
            LoginResponseDto dto = new LoginResponseDto("아이디 혹은 패스워드를 확인해 주세요", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        }
        Member member1= member.orElseThrow(()-> new IllegalArgumentException("nonono"));

        if (!passwordEncoder.matches(loginDto.getPassword(), member1.getPassword())) {
            LoginResponseDto dto = new LoginResponseDto("아이디 혹은 패스워드를 확인해 주세요", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);

        }
        TokenDto tokenDto = jwtUtil.createAllToken(loginDto.getUsername());

        TokenNicknameDto nicknameDto = new TokenNicknameDto(tokenDto.getAccessToken(), tokenDto.getRefreshToken(), member1.getNickname(),1004);

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByMemberUsername(loginDto.getUsername());
        if (refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().update(tokenDto.getRefreshToken()));
        } else {
            RefreshToken newRefreshToken = new RefreshToken(tokenDto.getRefreshToken(), loginDto.getUsername());
            refreshTokenRepository.save(newRefreshToken);
        }
        setHeader(response, tokenDto);


        return new ResponseEntity<>(nicknameDto ,HttpStatus.ACCEPTED);

    }
    private void setHeader(HttpServletResponse response, TokenDto tokenDto){
        String accessToken = "bearer "+tokenDto.getAccessToken();
        response.addHeader( JwtUtil.ACCESS_TOKEN,accessToken);
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }

    public ResponseEntity<?> checkNickname(String nickname) {
       Optional<Member> member = memberRepository.findByNickname(nickname);
       if(member.isPresent()){
           LoginResponseDto dto = new LoginResponseDto("이미 사용중인 닉네임 입니다", HttpStatus.BAD_REQUEST.value());
           return new ResponseEntity<>(dto,HttpStatus.BAD_REQUEST);
       }
       LoginResponseDto dto = new LoginResponseDto("사용 가능한 닉네임 입니다!", HttpStatus.OK.value());
       return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    public ResponseEntity<?> checkUsername(String username) {
        Optional<Member> member = memberRepository.findByUsername(username);
        if(member.isPresent()){
            LoginResponseDto dto = new LoginResponseDto("이미 사용중인 아이디 입니다", HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(dto,HttpStatus.BAD_REQUEST);
        }
        LoginResponseDto dto = new LoginResponseDto("사용 가능한 아이디 입니다!", HttpStatus.OK.value());
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    public LoginResponseDto getNickname(UserDetailsImpl userDetails) {
        return new LoginResponseDto(userDetails.getMember().getNickname(),HttpStatus.OK.value());
    }
}

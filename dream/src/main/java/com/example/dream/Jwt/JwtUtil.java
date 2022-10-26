package com.example.dream.Jwt;


import com.example.dream.dto.TokenDto;
import com.example.dream.entity.Member;
import com.example.dream.entity.RefreshToken;
import com.example.dream.repository.RefreshTokenRepository;
import com.example.dream.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserDetailsService userDetailsService;
    public static final String ACCESS_TOKEN = "Authorization";
    public static final String REFRESH_TOKEN = "Refresh_Token";
    private static final long ACCESS_TIME = 1000L * 60 * 60 * 1000; //1000HR
    private static final long REFRESH_TIME = 1000L * 60 * 60 * 24 * 2; //48HR


    @Value("${jwt.secret.key}")
    private String secretKey;

    private Key key;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init(){
        byte[] bytes= Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String getHeaderToken(HttpServletRequest request, String type){
        return type.equals("Access") ? request.getHeader(ACCESS_TOKEN) : request.getHeader(REFRESH_TOKEN);
    }

    public TokenDto createAllToken(String username){
        return new TokenDto(createToken(username, "Access"),createToken(username, "Refresh"));
    }
    public String createToken(String username, String type){
        Date date = new Date();

        long time = type.equals("Access") ? ACCESS_TIME : REFRESH_TIME;


        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(date.getTime()+time))
                .setIssuedAt(date)
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    //token verification
    public Boolean tokenValidation(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch(ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException ex){
            log.error(ex.getMessage());
            return false;
        }
    }

    //refresher token verification
    public Boolean refreshTokenValidation(String token){
        //1st validation
        if(!tokenValidation(token)){
            return false;
        }
        //DB token comparison
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByMemberUsername(getUsernameFromToken(token));
        return refreshToken.isPresent() && token.equals(refreshToken.get().getRefreshToken());
    }

    //make authentication object
    public Authentication createAuthentication(String username){
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    // get email from the token
    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public Member getMemberFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return null;
        }
        return ((UserDetailsImpl) authentication.getPrincipal()).getMember();
    }

}

package com.example.dream.controller;

import com.example.dream.repository.MemberRepository;
import com.example.dream.service.DummyService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DummyController {
    private static final Gson gson = new Gson();
    private final DummyService dummyService;
    private final MemberRepository memberRepository;


    @GetMapping("/dummy")
    public ResponseEntity<?> getDummy(){
        return new ResponseEntity<>(gson.toJson(memberRepository.findAll()), HttpStatus.OK);
    }



}

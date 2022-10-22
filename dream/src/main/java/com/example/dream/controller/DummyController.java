package com.example.dream.controller;

import com.google.gson.Gson;
import com.example.dream.service.DummyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class DummyController {
    private static final Gson gson = new Gson();
    private final DummyService dummyService;


    @GetMapping("/dummy")
    public ResponseEntity<?> getDummy(){
        return new ResponseEntity<>(gson.toJson(dummyService.getDummy()), HttpStatus.OK);
    }

}

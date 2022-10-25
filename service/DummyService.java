package com.example.dream.service;

import com.example.dream.entity.Dummy;
import com.example.dream.repository.DummyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DummyService {

    private final DummyRepository dummyRepository;

    @Transactional
    public List<Dummy> getDummy(){
        System.out.println(dummyRepository.findAll());
        return  dummyRepository.findAll();

    }

}

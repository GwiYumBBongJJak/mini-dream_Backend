package com.example.dream.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dummy {
    @Id
    @Column
    private long id;


    @Column
    private String msg;

}

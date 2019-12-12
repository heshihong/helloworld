package com.hsh.aop.entity;

import lombok.Data;

@Data
public class User {

    private String id;
    private String name;
    private Long age;

    public User(String id,String name,Long age){
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

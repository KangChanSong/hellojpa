package com.example.jpa.domain.dto;

import lombok.Getter;

@Getter
public class UserDto {

    private String username;
    private int age;

    public UserDto(String username, int age) {
        this.username = username;
        this.age = age;
    }
}

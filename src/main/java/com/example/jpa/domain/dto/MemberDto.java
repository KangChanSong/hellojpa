package com.example.jpa.domain.dto;

import lombok.*;

@ToString
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private String username;
    private int age;
}

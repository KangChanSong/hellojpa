package com.example.jpa.domain.relationship;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class A {

    @Id @GeneratedValue
    @Column(name = "a_id")
    private Long id;

    private String title;

    @OneToOne(mappedBy = "a")
    private B b;
}

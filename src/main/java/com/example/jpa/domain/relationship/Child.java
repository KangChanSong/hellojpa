package com.example.jpa.domain.relationship;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Child {

    @Id @GeneratedValue
    @Column(name = "child_id")
    private Long id;
    private String name;

    @OneToOne(mappedBy = "child")
    private Parent parent;
}


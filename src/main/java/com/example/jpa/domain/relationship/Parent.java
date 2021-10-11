package com.example.jpa.domain.relationship;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Parent {

    @Id @GeneratedValue
    @Column(name = "parent_id")
    private Long id;

    private String name;

    @OneToOne
    @JoinTable(name = "parent_child",
    joinColumns = @JoinColumn(name = "parent_id"),
    inverseJoinColumns = @JoinColumn(name = "child_id"))
    private Child child;
}

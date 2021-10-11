package com.example.jpa.domain.relationship;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@IdClass(ChildId.class)
public class Child {

    @Id
    @Column(name = "child_id")
    private String id;

    @Id
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    private String name;

}

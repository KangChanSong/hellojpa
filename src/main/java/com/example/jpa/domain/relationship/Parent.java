package com.example.jpa.domain.relationship;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Getter @Setter
@Entity
public class Parent {

    @Id
    @Column(name = "parent_id")
    private String id;

    private String name;
}

package com.example.jpa.domain.relationship;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class B {

    @Id
    private Long aId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "a_id")
    private A a;

    private String content;
}

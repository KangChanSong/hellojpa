package com.example.jpa.domain.relationship;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@IdClass(GrandChildId.class)
public class GrandChild {

    @Id
    @ManyToOne
    @JoinColumn(name = "child_id", referencedColumnName = "child_id")
    private Child child;

    @Id
    @Column(name = "grandchild_id")
    private String id;

    private String name;
}

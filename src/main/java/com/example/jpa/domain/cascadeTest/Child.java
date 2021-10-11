package com.example.jpa.domain.cascadeTest;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.boot.jaxb.internal.stax.JpaOrmXmlEventReader;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Child {
    @Id @GeneratedValue
    @Column(name = "child_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    private String name;

    public void addParent(Parent parent){
        this.parent = parent;
        parent.getChildren().add(this);
    }
}

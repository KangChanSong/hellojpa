package com.example.jpashop.domain;

import javax.persistence.*;

@Entity
public class Duck {
    @Id @GeneratedValue
    public Long id;

    private String name;

    @PrePersist
    public void prePersist(){
        System.out.println("Duck prePersist id = " + id);
    }

    @PostPersist
    public void postPersist(){
        System.out.println("Duck postPersists id = " + id);
    }

    @PostLoad
    public void postLoad(){
        System.out.println("postLoad");
    }

    @PreRemove
    public void preRemove(){
        System.out.println("preRemove");
    }

    @PostRemove
    public void postRemove(){
        System.out.println("postRemove");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}

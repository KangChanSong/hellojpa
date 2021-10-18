package com.example.jpashop.util;

import javax.persistence.*;
import java.util.Objects;

public class DefaultListener {

    @PrePersist
    public void prep(Object o){
        System.out.println("prePersist class => " + o.getClass().getSimpleName());
    }

    @PostPersist
    public void postp(Object o){
        System.out.println("postPersist class => " + o.getClass().getSimpleName());
    }

    @PostLoad
    public void postL(Object o){
        System.out.println("postLoad class => " + o.getClass().getSimpleName());
    }

    @PreRemove
    public void preR(Object o){
        System.out.println("preRemove class => " + o.getClass().getSimpleName());
    }

    @PostRemove
    public void postR(Object o){
        System.out.println("postRemove class => " + o.getClass().getSimpleName());
    }
}

package com.example.jpa.domain.main;

import com.example.jpa.domain.relationship.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Parent parent = new Parent();
        parent.setId("p_id");
        parent.setName("parent");
        em.persist(parent);

        ChildId childId = new ChildId();
        childId.setId("child_id");

        Child child = new Child();
        child.setId(childId);
        child.setParent(parent);
        child.setName("child");
        em.persist(child);

        em.flush(); em.clear();

        Child found = em.find(Child.class, childId);
        System.out.println(found);


        tx.commit();
        em.close();
        emf.close();
    }
}

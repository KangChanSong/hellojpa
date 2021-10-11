package com.example.jpa.domain.main;

import com.example.jpa.domain.relationship.Child;
import com.example.jpa.domain.relationship.Parent;

import javax.crypto.Cipher;
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
        parent.setName("parent");
        em.persist(parent);

        Child child = new Child();
        child.setName("child");
        em.persist(child);

        parent.setChild(child);
        child.setParent(parent);

        System.out.println("parent.getId() = " + parent.getId());
        System.out.println("child.getId() = " + child.getId());

        tx.commit();
        em.close();
        emf.close();
    }
}

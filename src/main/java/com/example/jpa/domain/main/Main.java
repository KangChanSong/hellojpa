package com.example.jpa.domain.main;

import com.example.jpa.domain.relationship.A;
import com.example.jpa.domain.relationship.B;

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

        A a = new A();
        a.setTitle("title");
        em.persist(a);

        B b = new B();
        b.setA(a);
        b.setContent("content");
        em.persist(b);

        System.out.println("(b.getA().getId() == a.getId()) = " + (b.getA().getId() == a.getId()));

        tx.commit();
        em.close();
        emf.close();
    }
}

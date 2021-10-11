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
        parent.setId("parent_id");
        parent.setName("parent");
        em.persist(parent);

        em.flush(); em.clear();

        em.find(Parent.class, "parent_id");

        Child child = new Child();
        child.setId("child_id");
        child.setParent(parent);
        child.setName("child");

        em.persist(child);

        em.flush(); em.clear();

        ChildId childId = new ChildId();
        childId.setId("child_id");
        childId.setParent("parent_id");

        Child foundChild = em.find(Child.class, childId);

        GrandChild grandChild = new GrandChild();
        grandChild.setId("gc_id");
        grandChild.setChild(foundChild);
        em.persist(grandChild);

        em.flush(); em.clear();

        GrandChildId grandChildId = new GrandChildId();
        grandChildId.setChild("child_id");
        grandChildId.setId("gc_id");

        GrandChild foundGC = em.find(GrandChild.class, grandChildId);

        System.out.println(foundGC.toString());

        tx.commit();
        em.close();
        emf.close();
    }
}

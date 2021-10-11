package com.example.jpa.domain.cascadeTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class CascadeMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Parent parent = new Parent();
        parent.setName("parent");

        Child child = new Child();
        child.addParent(parent);
        child.setName("child");

        em.persist(parent);

        Long parentId = parent.getId();

        em.flush(); em.clear();

        Parent foundParent = em.find(Parent.class, parentId);
        foundParent.getChildren().remove(0);

        tx.commit();
        em.close();
        emf.close();
    }
}

package com.example.jpa.domain.main;

import com.example.jpa.domain.relationship.Member;
import com.example.jpa.domain.relationship.embedded.Address;
import com.example.jpa.domain.relationship.embedded.Period;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class EmbeddedTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = em.find(Member.class, 1L);
        member.setHomeAddress(null);
        member.setWorkPeriod(null);


        tx.commit();
        em.close();
        emf.close();
    }
}

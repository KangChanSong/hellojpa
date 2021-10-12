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

        Member member = new Member();
        member.setName("member");

        Address home = new Address();
        home.setCity("city");
        home.setStreet("street");
        home.setZipcode("zcode");

        Address company = new Address();
        company.setCity("c_city");
        company.setStreet("c_street");
        company.setZipcode("c_zipcode");

        em.persist(member);
        tx.commit();
        em.close();
        emf.close();
    }
}

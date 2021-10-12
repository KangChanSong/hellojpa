package com.example.jpa.repository.criteria;

import com.example.jpa.domain.Address;
import com.example.jpa.domain.Member;
import com.example.jpa.domain.Order;
import com.example.jpa.domain.Team;
import com.example.jpa.main.Main;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Main.main(em -> {

            // 임베디드 값 타입 프로젝션 조회
            TypedQuery<Address> query = em.createQuery("select o.address from Order o", Address.class);
            Address address = query.getSingleResult();
            System.out.println("address = " + address.toString());

            // 스칼라 타입 프로젝션 조회
            TypedQuery<String> query1 = em.createQuery("select m.username from Member  m", String.class);
            String username = query1.getSingleResult();
            System.out.println("username = " + username);
        });
    }

    private void useTypedQuery(EntityManager em){

        TypedQuery<Member> typedQuery = em.createQuery("select m from Member as m", Member.class);

        Query query = em.createQuery("select m.username, m.age from Member m");

        List<Member> typedResult = typedQuery.getResultList();
        List<Object[]> result = query.getResultList();

        System.out.println("typedResult = " + typedResult.get(0).getClass().getSimpleName());
        System.out.println("result = " + result.get(0).getClass().getSimpleName());

        System.out.println("result.get(0)[0] = " + result.get(0)[0]);
        System.out.println("result.get(0)[1] = " + result.get(0)[1]);

    }
}

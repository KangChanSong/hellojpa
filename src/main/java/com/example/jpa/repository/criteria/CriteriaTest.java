package com.example.jpa.repository.criteria;

import com.example.jpa.domain.Member;
import com.example.jpa.main.Main;
import net.bytebuddy.agent.builder.AgentBuilder;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.function.Consumer;

public class CriteriaTest {
    public static void main(String[] args) {
        Main.main(em -> {
            TypedQuery<Member> typedQuery = em.createQuery("select m from Member as m", Member.class);

            Query query = em.createQuery("select m.username, m.age from Member m");

            List<Member> typedResult = typedQuery.getResultList();
            List<Object[]> result = query.getResultList();

            System.out.println("typedResult = " + typedResult.get(0).getClass().getSimpleName());
            System.out.println("result = " + result.get(0).getClass().getSimpleName());

            System.out.println("result.get(0)[0] = " + result.get(0)[0]);
            System.out.println("result.get(0)[1] = " + result.get(0)[1]);

        });
    }
}

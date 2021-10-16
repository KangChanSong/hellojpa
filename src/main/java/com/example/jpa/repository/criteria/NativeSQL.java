package com.example.jpa.repository.criteria;

import com.example.jpa.domain.Member;
import com.example.jpa.main.Main;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class NativeSQL {
    public static void main(String[] args) {
        Main.main(em -> {

        });
    }

    private static void select(EntityManager em){
        String sql = "select member_id, age, username, team_id from member where age > :age";
        Query nativeQuery = em.createNativeQuery(sql, Member.class)
                .setParameter("age", 30);

        List<Member> resultList = nativeQuery.getResultList();
        for (Member member: resultList) {
            System.out.println("member.getUsername() + \", \" + member.getAge() = " + member.getUsername() + ", " + member.getAge());
        }
    }
}

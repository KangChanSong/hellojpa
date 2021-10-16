package com.example.jpa.repository.criteria;

import com.example.jpa.domain.Member;
import com.example.jpa.domain.QMember;
import com.example.jpa.main.Main;
import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.jpa.domain.QMember.member;
public class QueryDslEx {
    public static void main(String[] args) {
        Main.main(em -> {

        });
    }

    private void useSubQuery(EntityManager em){
        QMember memberSub = new QMember("memberSub");
        JPAQuery query = new JPAQuery(em);
        List<Member> members = query.from(member)
                .where(member.in(
                        new JPASubQuery().from(memberSub)
                                .where(member.username.eq(memberSub.username))
                                .list(memberSub)
                ))
                .list(member);

        System.out.println("members.size() = " + members.size());
    }
}

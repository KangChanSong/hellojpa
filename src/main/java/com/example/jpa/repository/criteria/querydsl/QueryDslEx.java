package com.example.jpa.repository.criteria.querydsl;

import com.example.jpa.domain.Member;
import com.example.jpa.domain.QMember;
import com.example.jpa.domain.dto.MemberDto;
import com.example.jpa.main.Main;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Projections;

import java.util.List;

import static com.example.jpa.domain.QMember.member;

public class QueryDslEx {
    public static void main(String[] args) {
        Main.main(em -> {
            JPAQuery query = new JPAQuery(em);

        });
    }

    private static void useSubQuery(JPAQuery query){
        QMember memberSub = new QMember("memberSub");
        List<Member> members = query.from(member)
                .where(member.in(
                        new JPASubQuery().from(memberSub)
                                .where(member.username.eq(memberSub.username))
                                .list(memberSub)
                ))
                .list(member);

        System.out.println("members.size() = " + members.size());
    }

    private static void selectProjection(JPAQuery query){
        List<Tuple> tuples = query.from(member).list(member.username, member.age);
        for (Tuple tuple: tuples) {
            System.out.println("tuple.get(member.username) = " + tuple.get(member.username));
            System.out.println("tuple.get(member.age) = " + tuple.get(member.age));
        }
    }

    private static void selectCustomDto(JPAQuery query){
        List<MemberDto> dto = query.from(member)
                .list(
                        Projections.bean(MemberDto.class, member.username.as("username"), member.age));

        System.out.println("dto.toString() = " + dto.toString());
    }
}

package com.example.jpa.repository.criteria.querydsl;

import com.example.jpa.domain.Member;
import com.example.jpa.domain.QMember;
import com.mysema.query.annotations.QueryDelegate;
import com.mysema.query.types.expr.BooleanExpression;

public class MemberExpression {

    @QueryDelegate(Member.class)
    public static BooleanExpression isOlder(QMember member, int age){
        return member.age.gt(age);
    }
}

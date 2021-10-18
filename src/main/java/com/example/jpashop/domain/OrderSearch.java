package com.example.jpashop.domain;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import static com.example.jpashop.domain.OrderSpec.memberNameLike;
import static com.example.jpashop.domain.OrderSpec.orderStatusEq;
import static org.springframework.data.jpa.domain.Specifications.where;

public class OrderSearch {

    private String memberName;      //회원 이름
    private OrderStatus orderStatus;//주문 상태

    //Getter, Setter
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Specification<Order> toSpecification(){
        return where(memberNameLike(memberName))
                .and(orderStatusEq(orderStatus));
    }
}
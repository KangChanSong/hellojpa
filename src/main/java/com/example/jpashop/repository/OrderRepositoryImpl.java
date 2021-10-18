package com.example.jpashop.repository;

import com.example.jpashop.domain.Order;
import com.example.jpashop.domain.OrderSearch;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.List;

public class OrderRepositoryImpl extends QueryDslRepositorySupport implements CustomOrderRepository {

    public OrderRepositoryImpl(){
        super(Order.class);
    }

    @Override
    public List<Order> search(OrderSearch orderSearch) {

        return null;
    }
}

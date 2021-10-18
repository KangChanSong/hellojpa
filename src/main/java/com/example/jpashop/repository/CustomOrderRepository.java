package com.example.jpashop.repository;

import com.example.jpashop.domain.Order;
import com.example.jpashop.domain.OrderSearch;

import java.util.List;

public interface CustomOrderRepository {

    public List<Order> search(OrderSearch orderSearch);
}

package com.example.jpashop.repository;

import com.example.jpashop.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface ItemRepository extends JpaRepository<Item, Long>, QueryDslPredicateExecutor<Item> {
}

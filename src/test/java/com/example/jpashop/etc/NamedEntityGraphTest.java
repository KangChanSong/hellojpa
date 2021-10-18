package com.example.jpashop.etc;

import com.example.jpashop.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/appConfig.xml")
@Transactional
public class NamedEntityGraphTest {

    @Autowired
    EntityManager em;

    @Test
    public void NamedEntityGraph_테스트(){

        Order order = new Order();
        em.persist(order);

        EntityGraph graph = em.getEntityGraph("Order.withMember");

        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", graph);

        Order found = em.find(Order.class, order.getId(), hints);
    }
}

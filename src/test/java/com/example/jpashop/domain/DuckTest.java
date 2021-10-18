package com.example.jpashop.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/appConfig.xml")
@Transactional
public class DuckTest {

    @Autowired
    EntityManager em;

    @Test
    public void 리스너_테스트(){
        //given
        Duck duck = new Duck();
        duck.setName("name");
        //when
        em.persist(duck);
        em.find(Duck.class, duck.getId());
        em.remove(duck);
        //then
    }
}
package com.example.jpashop.repository;

import com.example.jpashop.domain.item.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Item item){
        if(item.getId() == null) {
            em.persist(item);
        } else {
            // 이미 한번 영속화 되었던 엔티티로 판단하기 때문에 수정(병합)한다.
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}

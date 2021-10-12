package com.example.jpa.repository.criteria;

import com.example.jpa.domain.Address;
import com.example.jpa.domain.Member;
import com.example.jpa.domain.Order;
import com.example.jpa.domain.Team;
import com.example.jpa.domain.dto.UserDto;
import com.example.jpa.main.Main;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) {
        Main.main(em -> {

        });
    }

    private void useTypedQuery(EntityManager em){

        TypedQuery<Member> typedQuery = em.createQuery("select m from Member as m", Member.class);

        Query query = em.createQuery("select m.username, m.age from Member m");

        List<Member> typedResult = typedQuery.getResultList();
        List<Object[]> result = query.getResultList();

        System.out.println("typedResult = " + typedResult.get(0).getClass().getSimpleName());
        System.out.println("result = " + result.get(0).getClass().getSimpleName());

        System.out.println("result.get(0)[0] = " + result.get(0)[0]);
        System.out.println("result.get(0)[1] = " + result.get(0)[1]);

    }

    private void selectProjection(EntityManager em){
        // 임베디드 값 타입 프로젝션 조회
        TypedQuery<Address> query = em.createQuery("select o.address from Order o", Address.class);
        List<Address> address = query.getResultList();
        System.out.println("address = " + address.get(0).toString());

        // 스칼라 타입 프로젝션 조회
        TypedQuery<String> query1 = em.createQuery("select m.username from Member  m", String.class);
        List<String> username = query1.getResultList();
        System.out.println("username = " + username.get(0));

        // NEW 커맨드로 조회
        TypedQuery<UserDto> query2 = em.createQuery("select new com.example.jpa.domain.dto.UserDto(m.username, m.age) from Member m", UserDto.class);
        List<UserDto> userDto = query2.getResultList();
        System.out.println("userDto.getUsername() = " + userDto.get(0).getUsername());
        System.out.println("userDto.getAge() = " + userDto.get(0).getAge());
    }

    private void usePagination(EntityManager em){
        TypedQuery<Member> query = em.createQuery("select m from Member m order by m.age desc", Member.class);
        query.setFirstResult(1);
        query.setMaxResults(10);

        List<Member> resultList = query.getResultList();
        resultList.forEach(member -> {
            System.out.println("member = " + member.getAge());
        });
    }
}

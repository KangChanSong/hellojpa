package com.example.jpa.repository.criteria;

import com.example.jpa.domain.Address;
import com.example.jpa.domain.Member;
import com.example.jpa.domain.Team;
import com.example.jpa.domain.dto.Aggregate;
import com.example.jpa.domain.dto.UserDto;
import com.example.jpa.main.Main;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

public class HibernateEx {
    public static void main(String[] args) {
        Main.main(em -> {

            Member member1 = em.find(Member.class, 1L);

            Member clone = cloneMember(member1);

            em.createQuery("update Member m set m.username =: username where m.id =1")
                    .setParameter("username", "newnew")
                    .executeUpdate();
            

            Member member2 = em.createQuery("select m from Member m where m.id = 1", Member.class)
                    .getSingleResult();

            System.out.println("clone.getUsername() = " + clone.getUsername()); // old
            System.out.println("member2.getUsername() = " + member2.getUsername()); // new

        });
    }

    private static Member cloneMember(Member member){
        Member m = new Member();
        m.setUsername(member.getUsername());
        m.setAge(member.getAge());
        return m;
    }

    private static void useTypedQuery(EntityManager em){

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

    private void useAggregateFunction(EntityManager em){
        TypedQuery<Aggregate> query = em.createQuery(
                "select new com.example.jpa.domain.dto.Aggregate(count (m), sum(m.age), avg(m.age), max(m.age), min(m.age)) " +
                        "from Member m",Aggregate.class);
        Aggregate aggregate = query.getSingleResult();

        System.out.println("aggregate.getCount() = " + aggregate.getCount());
        System.out.println("aggregate.getSum() = " + aggregate.getSum());
        System.out.println("aggregate.getAvg() = " + aggregate.getAvg());
        System.out.println("aggregate.getMax() = " + aggregate.getMax());
        System.out.println("aggregate.getMin() = " + aggregate.getMin());
    }

    private void useDistinct(EntityManager em){
        // 현재 team 은 하나, Member 는 100개
        // 아래 쿼리 실행시 List<Team>.size() 가 100임
        // DISTINCT 를 사용하면 데이터베이스에 DISTINCT 쿼리를 날리고
        // 또 애플리케이션에서 중복을 제거함
        // 그래서 Team 을 백개 가져온 후 DISTINCT 로 애플리케이션에서 하나만 남김.
        TypedQuery<Team> query = em.createQuery("select t from Team t join fetch t.members", Team.class);
        List<Team> team = query.getResultList();
        System.out.println("DISTINCT 사용 안할 시 team.size() = " + team.size()); // 100

        TypedQuery<Team> query1 = em.createQuery("select distinct t from Team t join fetch t.members", Team.class);
        List<Team> team1 = query1.getResultList();
        System.out.println("DISTINCT 사용 시 team.size() = " + team1.size()); // 1
    }

    private void useSingleAssociatedValue(EntityManager em){
        // 단일 값 연관 필드로 경로 탐색을 하면 SQL에서 내부조인이 묵시적으로 일어난다.
        List<Member> members = em.createQuery("select o.member from Order o", Member.class).getResultList();
        members.get(0).getTeam();
    }

    private void useSubquery(EntityManager em){
        // JPQL 에서는 WHERE, HAVING 절에서만 서브쿼리 사용 가능
        // EXISTS, ANY, ALL, IN 절에 서브쿼리 사용 가능
        TypedQuery<Member> query = em.createQuery("select m from Member m where m.age > (select  avg(m2.age) from Member m2)", Member.class);
        List<Member> members = query.getResultList();
        members.forEach(member -> {
            System.out.println("member.getAge() = " + member.getAge());
        });
    }


    private void useCollectionOperation(EntityManager em){
        // 컬렉션 식 (is empty, member of)
        List<Member> members =
                em.createQuery("select m from Member m where m.orders is not empty ", Member.class).getResultList();
        System.out.println("members.size() = " + members.size()); // 1

        List<Team> team = em.createQuery("select t from Team t where :memberParam member of t.members", Team.class)
                .setParameter("memberParam", members.get(0))
                .getResultList();
        System.out.println("team.size() = " + team.size());
    }

    private static void useNamedQuery(EntityManager em){
        List<Member> members = em.createNamedQuery("Member.findByUsername", Member.class)
                .setParameter("username", "member10")
                .getResultList();

        System.out.println("members.size() = " + members.size());

        Integer count = em.createNamedQuery("Member.count", Integer.class).getSingleResult();
        System.out.println("count = " + count);
    }

    private static void bulk(EntityManager em){
        //벌크연산
        // 영속성 컨텍스트를 무시하고 DB에 직접 쿼리
        em.createQuery("update Member m set m.username = '아저씨' where m.age > :age")
                .setParameter("age", 50)
                .executeUpdate();
    }

    private static void emRefresh(EntityManager em){
        Member member = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "아저씨")
                .getResultList().get(0);

        System.out.println("member.getAge() = " + member.getAge());

        em.createQuery("update Member m set m.age = 20 where m.username =: username")
                .setParameter("username", "아저씨")
                .executeUpdate();

        em.refresh(member);
        System.out.println("member.getAge() = " + member.getAge());
    }
}

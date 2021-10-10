package hellojpa;

import hellojpa.domain.Member;
import hellojpa.domain.Member2;
import util.QueryRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.function.Consumer;

import static hellojpa.RoleType.ADMIN;

public class JpaMain {

    public static void main(String[] args) {
        QueryRunner.execute( em -> saveMember2(em));

    }

    private static void saveMember2(EntityManager em){
        Member2 member2 = new Member2();
        member2.setId(1L);
        member2.setUsername("asd");
        em.persist(member2);
    }

    private static void save(EntityManager em){
        Member member = new Member();
        member.setId(1L);
        member.setUsername("asd");
        member.setAge(10);
        member.setRoleType(ADMIN);

        em.persist(member);
    }
}


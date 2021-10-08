package hellojpa;

import net.bytebuddy.matcher.MethodReturnTypeMatcher;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member(5L, "m5");
            em.persist(member);

            member.setName("chane");

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private void persist(EntityManager em, Long id, String name){
        Member member = new Member();
        member.setId(id);
        member.setName(name);
        em.persist(member);
    }
}


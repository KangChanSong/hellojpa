import com.example.jpa.domain.relationship.Member;
import com.example.jpa.domain.relationship.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ProxyTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Member member = new Member();
        member.setName("asdsa");
        em.persist(member);

        Long id = member.getId();

        em.flush(); em.clear();

        Member proxy = em.getReference(Member.class, id);
        boolean loaded = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(proxy);
        System.out.println("loaded = " + loaded);

        proxy.getName();
        loaded = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(proxy);
        System.out.println("loaded = " + loaded);

        tx.commit();
        em.close();
        emf.close();
    }
}

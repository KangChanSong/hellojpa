package com.example.jpashop.etc;

import com.example.jpashop.domain.Member;
import com.example.jpashop.domain.Order;
import com.example.jpashop.domain.OrderItem;
import com.example.jpashop.domain.item.Book;
import com.example.jpashop.domain.item.Item;
import com.example.jpashop.domain.visitors.PrintVisitor;
import com.example.jpashop.domain.visitors.TitleVisitor;
import com.example.jpashop.repository.MemberRepository;
import com.example.jpashop.service.MemberService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/appConfig.xml")
public class EntityManagerLifeCycleTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;

    @Test
    public void 엔티티매니저_생명주기_동일성_테스트(){
        //given
        Member member = new Member();
        member.setName("asd");
        memberService.join(member);
        //when
        Member m1 = memberService.findOne(member.getId());
        Member m2 = memberRepository.findOne(member.getId());
        //then
        assertFalse(m1 == m2);
    }

    @Transactional
    @Test
    public void 프록시_원본_등일성_테스트(){
        //given
        Member member = new Member();
        member.setName("asd");
        memberRepository.save(member);
        em.flush();
        em.clear();

        //when
        Member ref = em.getReference(Member.class, member.getId());
        Member member1 = em.find(Member.class, member.getId());

        //then
        assertTrue(ref == member1);
        assertFalse(member1.getClass() == Member.class);
    }

    @Transactional
    @Test
    public void equals_테스트(){
        //given
        Member member = new Member();
        member.setName("asd");
        em.persist(member);
        em.flush();
        em.clear();

        //when
        Member member1 = em.find(Member.class, member.getId());
        //then
        assertTrue(member.equals(member1));
    }

    @Transactional
    @Test
    public void 부모타입으로_프록시조회(){
        //given
        Book saveBook = new Book();
        saveBook.setName("book");

        em.persist(saveBook);
        em.flush();
        em.clear();

        //when
        Item proxyItem = em.getReference(Item.class, saveBook.getId());
        System.out.println("proxyItem = " + proxyItem.getClass());

        if(proxyItem instanceof Book){
            System.out.println("proxyItem instanceof Book");
            Book book = (Book) proxyItem;
            System.out.println("책 저자 = " + book.getAuthor());
        }

        //then
        assertFalse(proxyItem.getClass() == Book.class);
        assertFalse(proxyItem instanceof Book);
        assertTrue(proxyItem instanceof Item);
    }

    @Transactional
    @Test
    public void 상속관계와_프록시_도메인모델(){
        //given
        Book book = new Book();
        book.setName("jpa");
        book.setAuthor("김영한");
        em.persist(book);

        OrderItem saveOrderItem = new OrderItem();
        saveOrderItem.setItem(book);
        em.persist(saveOrderItem);

        em.flush();
        em.clear();
        //when
        OrderItem orderItem = em.find(OrderItem.class, saveOrderItem.getId());
        Item item = orderItem.getItem();

        System.out.println("item.getClass() = " + item.getClass());
        //then
        assertFalse(item.getClass() == Book.class);
        assertFalse(item instanceof Book);
        assertTrue(item instanceof Item);
    }

    @Transactional
    @Test
    public void visitor_패턴(){
        //given
        Book book = new Book();
        book.setName("jpa");
        book.setAuthor("김영한");
        em.persist(book);

        OrderItem saveOrderItem = new OrderItem();
        saveOrderItem.setItem(book);
        em.persist(saveOrderItem);

        em.flush();
        em.clear();

        //when
        Item item = saveOrderItem.getItem();
        item.accept(new PrintVisitor());
    }

    @Transactional
    @Test
    public void 영속성_컨텍스트가_조회된_프록시_유지(){
        //given
        Member member = new Member();
        member.setName("asd");
        em.persist(member);
        em.flush();
        em.clear();

        //when
        System.out.println("============== 프록시 획득 ============");
        Member proxy = em.getReference(Member.class, member.getId());
        System.out.println("========== 조회 쿼리 ===========");
        proxy.getName();
        System.out.println("proxy = " + proxy.getClass()); // 조회 쿼리를 날렸어도 여전히 프록시다.
        assertTrue(proxy.getClass() != Member.class);
        em.clear();

        System.out.println("============== 원본 획득 ============");
        Member sample = em.find(Member.class, member.getId());
        assertTrue(sample.getClass() == Member.class);
    }

    @Transactional
    @Test
    public void N_플러스_1_테스트(){
        //given
        // JPQL 은 "독립적으로" SQL로 해석되어 실행된다.
        // 10개의 멤버를 모두 가져오고, orders 가 즉시로딩으로 설정되어 있는 것을 확인 후 orders 를 조회하는 쿼리를 날리기 시작한다.

        IntStream.range(1, 10).forEach(i -> {
            Member member = new Member();
            member.setName("name" + 1);
            em.persist(member);
        });

        em.flush();
        em.clear();
        //when
        em.createQuery("select m from Member m", Member.class)
                .getResultList();

    }

    @Transactional
    @Test
    public void N_플러스_1_테스트_2(){
        //given
        IntStream.range(1, 10).forEach(i -> {
            Member member = new Member();
            member.setName("name" + 1);
            em.persist(member);
            Order order = new Order();
            order.setMember(member);
            em.persist(order);
        });

        em.flush();
        em.clear();

        //when
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        members.forEach(m -> m.getOrders().get(0).getTotalPrice());

    }
}

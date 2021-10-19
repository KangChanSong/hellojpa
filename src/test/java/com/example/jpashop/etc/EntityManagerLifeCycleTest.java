package com.example.jpashop.etc;

import com.example.jpashop.domain.Member;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
        assertNotEquals(m1, m2);
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
        assertEquals(ref, member1);
        System.out.println("member1.getClass().getSimpleName() = " + member1.getClass().getSimpleName());
    }

}

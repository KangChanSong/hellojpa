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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/appConfig.xml")
public class EntityManagerLifeCycleTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    public void 동일성_테스트(){
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

}

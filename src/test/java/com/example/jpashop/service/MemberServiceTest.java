package com.example.jpashop.service;

import com.example.jpashop.domain.Member;
import com.example.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
@Transactional // 테스트의 @Transactional 은 각각의 테스트가 끝나면 강제로 롤백함.
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");
        //when
        Long saveId = memberService.join(member);
        //then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
        Member member = new Member();
        member.setName("name");
        Member member_ = new Member();
        member_.setName("name");
        //when
        memberService.join(member);
        memberService.join(member_);
        //then
        fail("예외가 발생해야 함");
    }

}
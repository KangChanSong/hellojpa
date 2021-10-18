package com.example.jpashop.repository;

import com.example.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(String name);
}

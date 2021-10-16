package com.example.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
//@NamedQueries({
//        @NamedQuery(
//                name = "Member.findByUsername",
//                query = "select m from Member m where m.username =: username"
//        ),
//        @NamedQuery(
//                name = "Member.count",
//                query = "select count(m) from Member m"
//        )
//})
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}

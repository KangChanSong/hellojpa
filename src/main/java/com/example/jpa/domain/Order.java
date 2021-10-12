package com.example.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@SequenceGenerator(name = "seq_order_gen" , sequenceName = "seq_order")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_order_gen")
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private int orderAmount;

    @Embedded
    private Address address;
}

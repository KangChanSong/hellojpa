package com.example.jpa.domain.relationship;

import com.example.jpa.domain.relationship.embedded.Address;
import com.example.jpa.domain.relationship.embedded.Period;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Period workPeriod;

    @Embedded
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city", column = @Column(name = "company_city")),
            @AttributeOverride(name="street", column = @Column(name = "company_street")),
            @AttributeOverride(name="zipcode", column = @Column(name = "company_zipcode"))
    })
    private Address companyAddress;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    private void addOrder(Order order){
        this.orders.add(order);
        order.setMember(this);
    }
}

package com.example.jpa.domain.relationship;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Delivery extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne
    private Order order;

    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus status;

    public void setOrder(Order order) {
        this.order = order;
        order.setDelivery(this);
    }
}

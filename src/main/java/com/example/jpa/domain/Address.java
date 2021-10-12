package com.example.jpa.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Embeddable;

@ToString
@Getter
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

    public Address(){}
    public Address(String city, String street, String zipcode){
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}

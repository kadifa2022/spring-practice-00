package com.cydeo.entity;

import javax.persistence.Entity;

@Entity
public class Address {

    private String name;
    private String street;
    private String zipCode;
    private Customer customerId;


}

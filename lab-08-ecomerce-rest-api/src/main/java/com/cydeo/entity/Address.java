package com.cydeo.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
public class Address extends BaseEntity{
    private String name;
    private String zipCode;
    private String street;
    @ManyToOne
    private Customer customer;
}

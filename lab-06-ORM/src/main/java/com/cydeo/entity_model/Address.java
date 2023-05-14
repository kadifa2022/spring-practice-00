package com.cydeo.entity_model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id; // we use another id because is different data
    private String name;
    private String street;
    private String zipCode;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;


}

package com.cydeo.entity_model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends BaseEntity{


    private String firstName;
    private String lastName;
    private String userName;
    private String email;



}

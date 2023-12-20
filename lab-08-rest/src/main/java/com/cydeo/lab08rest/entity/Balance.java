package com.cydeo.lab08rest.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Balance extends BaseEntity{
    @OneToOne
    private Customer customer;
    private BigDecimal amount;
}

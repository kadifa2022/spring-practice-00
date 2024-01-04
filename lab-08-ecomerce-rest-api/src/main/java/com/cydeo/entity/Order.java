package com.cydeo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order extends BaseEntity{
    @ManyToOne
    private Cart cart;
    private BigDecimal paidPrice;
    private BigDecimal totalPrice;
    @ManyToOne
    private Customer customer;
    @OneToOne(cascade = CascadeType.ALL)//we have error we put CascadeType.all -> payment is parent table
    private Payment payment;
}

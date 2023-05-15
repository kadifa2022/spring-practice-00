package com.cydeo.entity_model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "orders") // changing name of Order, because Order is reserved word
public class Order extends BaseEntity{

    private BigDecimal paidPrice;
    private BigDecimal totalPrice;
    @OneToOne
    private Cart cart;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    @OneToOne
    private Payment payment;

    @Override
    public String toString() {
        return "Order{" +
                "paidPrice=" + paidPrice +
                ", totalPrice=" + totalPrice +
                ", cart=" + cart +
                ", customer=" + customer +
                ", payment=" + payment +
                '}';
    }
}

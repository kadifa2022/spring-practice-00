package com.cydeo.lab08rest.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class CartItem extends BaseEntity {
    @ManyToOne
    private Product product;
    private Integer quantity;
    @ManyToOne
    private Cart cart;
}

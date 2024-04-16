package com.cydeo.entity;

import com.cydeo.enums.DiscountType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Discount extends BaseEntity {
    private DiscountType discountType;
    private String name;
    private BigDecimal discount;

}

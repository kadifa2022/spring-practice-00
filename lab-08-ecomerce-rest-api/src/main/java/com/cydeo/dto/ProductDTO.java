package com.cydeo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private BigDecimal price;
    private Integer quantity;
    private Integer remainingQuantity;
    private String name;

}

package com.cydeo.dto;


import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UpdateOrderDTO {

    // this is potential problems and we are adding one additional layer

    //only updatable fields should be existed in DTO
    @NotNull(message = "price can not be null")
    @Positive(message ="price shouldn't be negative")
    @DecimalMax(value = "100000", message = "price can not be grater than 1000000")
    @DecimalMin(value = "0.1", message ="price can not be less than 0.1")
    private BigDecimal paidPrice;

    @NotNull(message = "totalPrice can not be null")
    @Positive(message ="totalPrice shouldn't be negative")
    @DecimalMax(value = "100000", message = "totalPrice can not be grater than 1000000")
    @DecimalMin(value = "0.1", message ="totalPrice can not be less than 0.1")
    private BigDecimal totalPrice;



}

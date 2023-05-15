package com.cydeo.repository;

import com.cydeo.entity_model.Discount;
import com.cydeo.enums.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    // Write a derived query to get discount by name
    Discount findFirstByName(String name);// choose first to get one name

    //Write a derived query to get all discounts grater than discount amount
    List<Discount> findAlByDiscountGreaterThan(BigDecimal amount);

    // Write a derived query to get all discounts by specific discount type
    List<Discount> findAllByDiscountType(DiscountType discountType);

    // Write a JPQL query to get all discounts amount between range of discount amount
    @Query("SELECT d FROM Discount d WHERE d.discount between ?1 AND ?2")
    List<Discount> findAllByRangeBetweenAmount(BigDecimal startAmount, BigDecimal endAmount);

}

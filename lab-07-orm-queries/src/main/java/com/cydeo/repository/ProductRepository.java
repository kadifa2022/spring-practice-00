package com.cydeo.repository;

import com.cydeo.entity_model.Category;
import com.cydeo.entity_model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    //Write a derived query to get top 3 product order by price desc
    List<Product> findTop3ByOrderByPriceDesc();

    //Write a derived query to get product by specific name
    Product findFirstByName(String name);

    //Write a derived query to get product by specific category
    List<Product> findAllByCategoryListContaining(Category category );//manyToMany relations than we need list

    //Write a derived query to get count by price greater than specific amount
    Integer countProductByPriceGreaterThan(BigDecimal price);

    //Write a derived query to get all product by quantity greater than or equals specific count
    List<Product> findAllByQuantityIsGreaterThanEqual(int quantity);

    //Write a native query to get all product by price greater than specific amount and quantity lower than specific count
    @Query(value = "SELECT * FROM product p WHERE p.price>?1 AND p.remaining_quantity<?2 ", nativeQuery = true)
    List<Product> retrieveProductListGreaterThanPriceAndLoverThanAndRemainingQuantity(BigDecimal price, int remainingQuantity);

    //Write a native query to get all product by specific category id
    @Query(value = "SELECT * FROM product p JOIN product_category_rel pl ON pl.p_id WHERE pl.c_id = ?1", nativeQuery = true)
    List<Product> retrieveProductListByCategory(Long categoryId);

    //Write a native query to get all product by specific categoryId and price greater than specific amount
    @Query(value = "SELECT * FROM product p JOIN product_category_rel pl On pl.p_id = p.id WHERE pl.c_id IN (?1)" +
            "p.price>?2", nativeQuery = true)
    List<Product> retrieveProductListByCategory(List<Long> categoryId, BigDecimal price);

}

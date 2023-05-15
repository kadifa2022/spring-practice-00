package com.cydeo.repository;

import com.cydeo.entity_model.CartItem;
import com.cydeo.enums.CartState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Write a derived query to get count cart
    Integer countAllBy();
    Integer countCartItemBy();

    //Write a derived query to get cartItems for specific cart state
    //ENUM
    List<CartItem> findAllByCart_CartState(CartState cartState);

    //Write a native query to get cartItems for specific cart state and product name
    @Query(value = " SELECT * FROM cart_item ci JOIN cart c ON ci.cart_id = c.id  JOIN product p " +
            "ON ci.product_id = p.id WHERE c.cart_state =?1 and p.name = ?2" , nativeQuery = true)
    List<CartItem> retrieveCartItemsByCartStateAndProductName(@Param("cartState") String  cartState, @Param("name") String name);// if is object enum type String

    //Write a native query to get cartItems for specific cart state and without discount

    //Write a native query to get cartItems for specific cart state and with specific Discount type

}

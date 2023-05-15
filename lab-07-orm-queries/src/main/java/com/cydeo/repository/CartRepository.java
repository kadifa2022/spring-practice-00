package com.cydeo.repository;

import com.cydeo.entity_model.Cart;
import com.cydeo.enums.CartState;
import com.cydeo.enums.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    //Write derived query to get all cart by specific discount type
    List<Cart> findAllByDiscount_DiscountType(DiscountType discountType); //derived query understand ENUM  as String , FORNative query OR JPQL WE NEED TO PUT String and value


    //Write a JPQL query to get all cart by customer
    @Query("SELECT  c FROM Cart c Where c.customer.id = ?1")
    List<Cart> retrieveCartListByCustomer(Long id);

    // Write derived query to get all cart by customer and cart state
    List<Cart> findAllByCustomer_IdAndCartState(Long id, CartState cartState);

    // Write a derived query to get all cast by customer and cart state and discount is null condition
    List<Cart> findAllByCustomer_IdAndCartStateAndDiscountIsNull(Long id, CartState cartState);

    //Write a native query to get all cart by customer and cart state and discount is not null condition
    @Query(value = "SELECT * FROM cart c JOIN costomer cu ON c.customer_id = cu.id WHERE " +
            "c.cart_state = ?1 AND cu.id = ?2 discount.id<> null", nativeQuery = true)
    List<Cart> findAllByCustomer_IdAndCartStateAndDiscountIsNull(String cartState, Long id);



}

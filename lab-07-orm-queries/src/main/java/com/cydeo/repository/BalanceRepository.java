package com.cydeo.repository;

import com.cydeo.entity_model.Balance;
import com.cydeo.entity_model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BalanceRepository extends JpaRepository<Balance, Long> {

    // Write a Derived query to check balance exists for specific customer
    boolean existsBalanceByCustomer(Customer customer);
    boolean existsByCostumerId(Long aLong);

    // Write a derived Query to get balance for specific
    Balance findByCustomer(Customer customer);
    Balance findByCustomer_Id(Long id);

    //Write a native  query to get top5 max balance
    @Query(value = "SELECT * FROM balance ORDER BY amount DESC LIMIT 5", nativeQuery = true)
    List<Balance> retrieveTop5Amount();

    //Write a derived query nto get all balances greater than equal specific balance amount
    List<Balance> findAllByAmountGreaterThanEqual(BigDecimal amount);
    //Write a native query to get all balances less than specific balance amount
    @Query(value = "SELECT * FROM balance WHERE amount <?1", nativeQuery = true)
    List<Balance> retrieveLessThanAmount(@Param("amount") BigDecimal amount);

}

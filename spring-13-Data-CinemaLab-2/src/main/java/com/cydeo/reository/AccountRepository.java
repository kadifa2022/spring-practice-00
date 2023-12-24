package com.cydeo.reository;

import com.cydeo.entity.Account;

import com.cydeo.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    //----------------Derived Queries---------------//

    // what is derived query?

    // Write a derived query to list all accounts with specific country or state
    List<Account> findAllByCountryOrState(String country, String state);

    //Write a derived query to list all accounts with age lower than or equal to specific value?
    List<Account> findAllByAgeLessThanEqual(Integer age );

    // Write a derived query to list all accounts with specific role
    List<Account> findByRole(UserRole role);

    // Write a derived query to list all accounts between a range of ages
    List<Account> findAllByAgeBetween(Integer ageStart, Integer ageEnd);

    //Write a derived query to list all accounts where the beginning of te address contains the keyword
    List<Account> findAllByAddressStartingWith(String keyword);

    // Write a derived query to sort the list of accounts with age
    List<Account> findAllByOrderByAge();


    //---------------------JPQL ----DB INDEPENDENT----slower, because need to convert to SQL --------------------//

    //Write a JPQL query tht returns all accounts
    @Query("SELECT a FROM Account a")
    List<Account> fetchAllByUsingJPQL();
    //Write a JPQL query to list all admin accounts
    @Query("SELECT a FROM Account a WHERE a.role = 'ADMIN'")
    List<Account> fetchAdminAccount();
    //Write a JPQL query to sort all accounts with age
    @Query("SELECT a FROM Account a ORDER BY a.age DESC ")
    List<Account> fetchAllOrderBasedOnAge();

    //-------------Native Queries---------faster --------------------//
    // based on DB

    //Write a native query to read all accounts with an age lower than specific value
    //first solution
//    @Query(value = "SELECT * FROM account_details WHERE age < ?1 ", nativeQuery = true)
//    List<Account> retrieveAllByAgeLover(Integer age);

    // second solution  and preferred
    @Query(value = "SELECT * FROM account_details WHERE age < :age ", nativeQuery = true)
    List<Account> retrieveAllByAgeLover(@Param("age") Integer age);

    // Write a native query to read all accounts with specific value can be containable in the name, address, country, state, city

    @Query(value = "SELECT * FROM  account_details WHERE name ILIKE concat('%',?1,'%')" +
            "OR address ILIKE concat('%',?1,'%')" +
            "OR country ILIKE concat('%',?1,'%')" +
            "OR state ILIKE concat('%',?1,'%')" +
            "OR city ILIKE concat('%',?1,'%')", nativeQuery = true)
    List<Account> retrieveBySearchCriteria(@Param("pattern") String pattern);

    //Write a native query to read all accounts with an age higher than a specific value

    @Query(value = "Select * FROM account_details WHERE age > :age", nativeQuery = true)
    List<Account> readAccountAllAgeHigher(@Param("age")Integer age);



}

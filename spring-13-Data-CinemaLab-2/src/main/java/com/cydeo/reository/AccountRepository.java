package com.cydeo.reository;

import com.cydeo.entity.Account;

import com.cydeo.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    //---------------------JPQL ----DB INDEPENDENT------------------------//
    //Write a JPQL query tht returns all accounts
    @Query("SELECT a FROM Account a")
    List<Account> fetchAllByUsingJPQL();
    //Write a JPQL query to list all admin accounts
    @Query("SELECT a FROM Account a WHERE a.role = 'ADMIN'")
    List<Account> fetchAdminAccount();
    //Write a JPQL query to sort all accounts with age
    @Query("SELECT a FROM Account a ORDER BY a.age DESC ")
    List<Account> fetchAllOrderBasedOnAge();



}

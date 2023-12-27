package com.cydeo.reository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //------------------Derived Queries---------------------//
    //Write a derived query to read a user with an email?
    Optional<User> findByEmail(String name);

    //Write a derived query to ead a user with a username?
    Optional<User> findByUserName(String username);

    //Write a derived query to list all users that contain a specific name?
    List<User> findAllByAccountNameContaining(String name);

    //Write a derived query to list all users that contain as specific name in  ignore case mode?
    List<User> findAllByAccountNameContainingIgnoreCase(String name);
    //Write a derived query to list all users with an age grater than a specific age?
    List<User> findAllByAccountAgeGreaterThan(Integer age);


    //---------------------JPQL --------------------------------//
    //write a JPQL Query that returns a user read by email?
    @Query("SELECT u FROM User u WHERE u.email= ?1")
    Optional<User> fetchByEmail(@Param("email") String email);
    //write a JPQL Query that returns a user read by username?
    @Query("SELECT u FROM User u WHERE u.username=?1")
    Optional<User> fetchByUsername(@Param("username") String username);
    //Write a JPQL Query that returns all users
    @Query("SELECT u FROM User u")
    List<User> fetchAllUser();

    //----------------------Native queries---------------------//

    //write a native query that returns all users that contain a specific name
    @Query(value = "SELECT * FROM user_account u JOIN account_details ad " +
            "ON ad.id = u.account-details_id WHERE ad.name ILIKE concat ('%',?1,'%')", nativeQuery = true)
    List<User> retrieveAllByName(@Param("name") String name);

    //Write a native query that returns all users
    @Query(value = "SELECT * FROM user_account", nativeQuery = true)
    List<User> retrieveAllByUsers();

    //Write a native query that returns all users in range of ages
    @Query(value = "SELECT * FROM user_account u JOIN  account_details ad " +
            "ON  ad.id = u.account_details_id WHERE ad.age BETWEEN ?1 AND ?2" , nativeQuery = true)
    List<User> retrieveBetweenAgeRange(@Param("age1") Integer  age1, @Param("age2") Integer age2);

    //Write a native query to read a user by email
    @Query(value = "SELECT * FROM user_account  WHERE email = ?1" , nativeQuery = true)
    User getUserByEmail(@Param("email") String email);





















}

package com.cydeo.reository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //------------------Derived Queries---------------------//

    //Write a derived query to read a user with an email?

    //Write a derived query to ead a user with a username?
    //Write a derived query to list all users that contain a specific name?
    //Write a derived query to list all users that contain as specific name in  ignore case mode?
    //Write a derived query to list all users with an age grater than a specific age?

    //---------------------JPQL --------------------------------//
    //write a JPQL Query that returns a user read by email?
    //write a JPQL Query that returns a user read by username?

}

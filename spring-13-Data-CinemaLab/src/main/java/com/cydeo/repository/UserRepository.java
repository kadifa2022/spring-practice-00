package com.cydeo.repository;

import com.cydeo.entity_model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //----------------DERIVED QUERIES-----------------------//

    //Write a derived query to read a user with email?
    //Write a derived query to read user with a username?
    //Write a derived query to list all users that contain a specific name?
    //Write a derived query to list all users that contain a specific name in ignore case mode?
    //Write a derived query to list all users with an age grater than a specified age?

}

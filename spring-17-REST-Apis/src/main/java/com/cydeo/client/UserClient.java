package com.cydeo.client;

import com.cydeo.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url ="https://jsonplaceholder.typicode.com/users", name="USER-CLIENT") // this url needs to be consumed when method called and will be assigned to list of users DTO
public interface UserClient {

    @GetMapping("/users")// when we call this  url end point will be executed and json will be created and assign to DTO
    List<User> getUsers();
}

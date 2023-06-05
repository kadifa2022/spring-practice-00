package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();//this method will be called in controller
    UserDTO findByUserName(String username);
    void save(UserDTO user);
    void deleteByUserName(String username);





}

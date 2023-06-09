package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();//this method will be called in controller
    UserDTO findByUserName(String username);
    void save(UserDTO user);
   // void deleteByUserName(String username);


    UserDTO update(UserDTO user);

    void delete(String username);// created new delete() for isDeleted purpose/ soft delete

    List<UserDTO> listAllByRole(String role);
}

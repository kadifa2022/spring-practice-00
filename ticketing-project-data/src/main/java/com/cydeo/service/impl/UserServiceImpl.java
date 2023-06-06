package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> userList = userRepository.findAll(Sort.by("firstName"));
        return userList.stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        User user =  userRepository.findByUserName(username);
        return userMapper.convertToDto(user);
    }

    @Override
    public void save(UserDTO user) {// we are saving one object don't need to use stream
        userRepository.save(userMapper.convertToEntity(user));

    }

    @Override
    public void deleteByUserName(String username) {
        userRepository.deleteByUserName(username);

    }

    @Override
    public UserDTO update(UserDTO user) {
        //find current user
        User user1 = userRepository.findByUserName(user.getUserName());
        // Map update user dto to entity
        User convertedUser = userMapper.convertToEntity(user);
        //setting converted user
        convertedUser.setId(user1.getId());
        //saving converted user
        userRepository.save(convertedUser);

        return findByUserName(user.getUserName());


    }

    @Override
    public void delete(String username) {// created delete method for soft delete
        //go to db and get the users with username
        User user = userRepository.findByUserName(username);
        //change the isDeleted field to true
        user.setIsDeleted(true);
        // saved the object in the db
        userRepository.save(user);

    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        List<User> users = userRepository.findByRoleDescriptionIgnoreCase(role);
        return users.stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }
}

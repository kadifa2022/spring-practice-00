package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.context.annotation.Lazy;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProjectService projectService;
    private final TaskService taskService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, @Lazy ProjectService projectService, @Lazy TaskService taskService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    public List<UserDTO> listAllUsers() {
       // List<User> userList = userRepository.findAll(Sort.by("firstName"));// we used with @Where on Entity class level
        List<User> userList = userRepository.findAllByIsDeletedOrderByFirstNameDesc(false);// give me all non deleted users
        return userList.stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        //User user =  userRepository.findByUserName(username);
        User user = userRepository.findByUserNameAndIsDeleted(username, false);//give me all non deleted users
        return userMapper.convertToDto(user);
    }

    @Override
    public void save(UserDTO user) {// we are saving one object don't need to use stream
        userRepository.save(userMapper.convertToEntity(user));

    }




//
//    @Override//we don't use this method -> is for hard delete
//    public void deleteByUserName(String username) {
//        userRepository.deleteByUserName(username);
//
//    }

    @Override
    public UserDTO update(UserDTO user) {
        //find current user
        User user1 = userRepository.findByUserNameAndIsDeleted(user.getUserName(), false);
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
        User user = userRepository.findByUserNameAndIsDeleted(username, false);
        if(checkIfUserCanBeDeleted(user)) {//boolean method that we created
            //change the isDeleted field to true
            user.setIsDeleted(true);
            user.setUserName(user.getUserName() + "-" + user.getId()); //harold@manage.com-2

            // saved the object in the db
            userRepository.save(user);
        }
    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        List<User> users = userRepository.findByRoleDescriptionIgnoreCaseAndIsDeleted(role, false);
        return users.stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }
    private boolean checkIfUserCanBeDeleted(User user){

        switch (user.getRole().getDescription()){

            case "Manager":
                List<ProjectDTO> projectDTOList = projectService.listAllNonCompletedByAssignedManager(userMapper.convertToDto(user));
                return projectDTOList.size() == 0;
            case "Employee":
                List<TaskDTO> taskDTOList = taskService.listAllNonCompletedByAssignedEmployee(userMapper.convertToDto(user));
            default:
                return true;
        }



    }


}

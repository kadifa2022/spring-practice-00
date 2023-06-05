package com.cydeo.service;

import com.cydeo.dto.RoleDTO;

import java.util.List;

public interface RoleService {

   List<RoleDTO> listAllRoles();// service Dto

   RoleDTO findById(Long id);//findById() just method
}

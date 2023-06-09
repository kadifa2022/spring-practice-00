package com.cydeo.repository;

import com.cydeo.entity.User;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByIsDeletedOrderByFirstNameDesc(Boolean deleted);
    User findByUserNameAndIsDeleted(String username, Boolean deleted);
    @Transactional
    void findByUserName(String username);

    List<User> findByRoleDescriptionIgnoreCaseAndIsDeleted(String description, Boolean deleted);

}

package com.cydeo.repository;

import com.cydeo.entity_model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long > {


}

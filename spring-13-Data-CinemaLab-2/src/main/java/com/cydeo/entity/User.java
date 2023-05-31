package com.cydeo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user_account")
public class User extends  BaseEntity{

    private String userName;
    private String password;
    private String email;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_detail_id")// changing fk name
    private Account account;

    



}

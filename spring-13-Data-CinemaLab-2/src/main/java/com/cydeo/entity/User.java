package com.cydeo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
//@Data
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_account")
public class User extends  BaseEntity{

    private String userName;
    private String password;
    private String email;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_detail_id")// changing fk name
    private Account account;


    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

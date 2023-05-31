package com.cydeo.entity_model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="user_account")
public class User extends  BaseEntity{

    private String email;
    private String password;
    private String username;
    @OneToOne(fetch= FetchType.LAZY)//getting object from db when is needed .LAZY
    @JoinColumn(name = "account_details_id")
    private Account account;


    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}

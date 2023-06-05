package com.cydeo.entity;

import com.cydeo.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor//because of Jpa
@Entity
@Table(name = "users")
public class User extends BaseEntity{


    private String firstName;
    private String lastName;

    private String userName;
    private boolean enabled;
    private String phone;

    @ManyToOne// F-Key Many user can have same role
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
}

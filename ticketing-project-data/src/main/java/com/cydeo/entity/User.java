package com.cydeo.entity;

import com.cydeo.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Getter
@Setter
@NoArgsConstructor//because of Jpa
@Entity
@Table(name = "users")
//@Where(clause = "is_deleted=false")//spring, any repository which is using user entity all query inside the
public class User extends BaseEntity{//SELECT * FROM users WHERE id = 4 AND is_deleted = false;


    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String userName;
    private String passWord;

    private boolean enabled;
    private String phone;

    @ManyToOne// F-Key Many user can have same role
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
}

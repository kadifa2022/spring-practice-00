package com.cydeo.entity;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

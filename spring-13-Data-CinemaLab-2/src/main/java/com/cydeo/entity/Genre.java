package com.cydeo.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;


@Entity
@Data
@NoArgsConstructor
public class Genre extends BaseEntity{

    private String name;

}

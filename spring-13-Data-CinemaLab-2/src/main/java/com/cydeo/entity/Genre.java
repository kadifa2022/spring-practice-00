package com.cydeo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@Data
@AllArgsConstructor
public class Genre extends BaseEntity{

    private String name;
}

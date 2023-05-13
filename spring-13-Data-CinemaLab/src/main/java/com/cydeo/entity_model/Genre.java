package com.cydeo.entity_model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Genre  extends BaseEntity{

    private String name;


}

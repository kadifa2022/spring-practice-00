package com.cydeo.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // will let postgres to create primary key
    private Long id;
    private LocalDateTime insertDateTime;
    private Long insertUserId;//by whom created
    private LocalDateTime lastUpdateTime;
    private Long lastUpdateUserId;
}

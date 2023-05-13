package com.cydeo.entity_model;

import com.cydeo.enums.MovieState;
import com.cydeo.enums.MovieType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor

public class Movie extends BaseEntity{

    private String name;
    @Column(columnDefinition = "DATE")
    private LocalDate releaseDate;
    private Integer duration;
    @Column(columnDefinition = "text")// no limit of characters
    private String summary;
    @Enumerated(EnumType.STRING)
    private MovieType type;
    @Enumerated(EnumType.STRING)
    private MovieState state;
    private BigDecimal price;

}

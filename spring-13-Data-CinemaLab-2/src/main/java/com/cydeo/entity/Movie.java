package com.cydeo.entity;

import com.cydeo.enums.MovieState;
import com.cydeo.enums.MovieType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Movie extends BaseEntity{

    private String name;
    @Column(columnDefinition = "DATE")
    private LocalDate releaseDate;

    private Integer duration;

    @Column(columnDefinition = "text")// no limit for text
    private String summary;

    @Enumerated(EnumType.STRING)
    private MovieState state;

    @Enumerated(EnumType.STRING)
    private MovieType type;

    private BigDecimal price;
    //creating 3rd table
    @ManyToMany
    @JoinTable(name = "movie_genre_rel",
    joinColumns = @JoinColumn(name = "movie_id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genreList; //if is many to many use List<> and will be third table
}// we can use set (because behind scene delete only one book and list delete all books and insert they again w/o  particular book

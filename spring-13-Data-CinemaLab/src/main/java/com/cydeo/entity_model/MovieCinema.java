package com.cydeo.entity_model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class MovieCinema extends BaseEntity{
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY)
    private Cinema cinema;

    @Override
    public String toString() {
        return "MovieCinema{" +
                "dataTime=" + dateTime +
                '}';
    }
}

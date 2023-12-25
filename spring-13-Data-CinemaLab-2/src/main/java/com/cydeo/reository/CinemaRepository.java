package com.cydeo.reository;

import com.cydeo.entity.Cinema;
import com.cydeo.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    //-------------------------Derived Queries------------------//
    //Write a derived query to get cinema with a specific name
    Optional<Cinema> findByName(String name); // to avoid potential NullPointerException if is only one entity  like id, name

    //Write a derived query to read sorted the top 3 cinemas that contains a specific sponsored name
    List<Cinema> findFirst3BySponsorNameContainingOrderBySponsorName (String sponsoredName);

    //Write a derived query to list all cinemas in a specific country
    List<Cinema> findCinemaByLocationCountry(String country); // behind the scene is join the table Location

    //Write a derived query ti list all cinemas with a specific name or sponsored name
    List<Cinema> findAllByNameOrSponsorName(String name, String sponsorName);

    //----------------------JPQL QUERIES------------------------//
    //Write a JPQL query to read the cinema name with a specific id

    //-----------------------Native Queries-------------------//
}

package com.cydeo.reository;

import com.cydeo.entity.Cinema;
import com.cydeo.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    @Query("SELECT c.name FROM Cinema c Where c.id =?1")
    String  fetchCinemaById(@Param("id") Long id);

    //-----------------------Native Queries-------------------//

    //Write a native query to read all cinema by location Country
    @Query(value = "SELECT * FROM cinema c JOIN location l " +
            "ON c.location_id = l.id WHERE l.country = ?1", nativeQuery = true)
    List<Cinema> retrieveAllBasedOnLocationCountry(@Param("locationCountry") String locationCountry);

    //Write a native query to read all cinemas by name or sponsor name contains a specific pattern
    @Query(value = "SELECT * FROM cinema WHERE name ILIKE Lconcat ('%',?1,'%') " +
            "OR sponsor_name ILIKE concat('%',?1,'%')", nativeQuery = true)
    List<Cinema> retrieveAlLByNameOrSponsorName(@Param("pattern") String pattern);

    // Write a native query to sort all cinemas by name
    @Query(value = "SELECT  * FROM cinema ORDER BY name", nativeQuery = true)
    List<Cinema> sortByName();

    //Write a native query to distinct all cinemas by sponsor name
    @Query(value = "SELECT DISTINCT sponsored_name FROM cinema", nativeQuery = true)
    List<String> distinctBySponsoredName();
}

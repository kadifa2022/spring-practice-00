package com.cydeo.repository;

import com.cydeo.entity_model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Write a derived query to get category by name
    Category findByName(String name);

    // Write a derived query to get top 3 categories in order by desc
    List<Category> findTop3ByOOrderByNameDesc();
}

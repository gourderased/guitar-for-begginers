package com.example.guitarforbegginers.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("SELECT c FROM Category c")
    List<Category> findCategories();

    @Query("SELECT c FROM Category c where c.id = :id")
    Category findCategoryById(@Param("id") Long id);
}

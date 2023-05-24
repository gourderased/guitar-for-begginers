package com.example.guitarforbegginers.product;

import com.example.guitarforbegginers.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{

    @Query("SELECT p FROM Product p where p.id= :id")
    Product findByProductId(@Param("id") Long id);

//    @Query("select p from Product p")
//    List<Product> findProducts();

    @Query("SELECT p FROM Product p where p.category= :category")
    List<Product> findProductsByCategory(@Param("category") Category category);

}

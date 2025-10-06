package com.japonbaligi.letgoclone.repository;

import com.japonbaligi.letgoclone.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByTitleContainingIgnoreCase(String title);

    List<Product> findByCategory(String category);

    List<Product> findByLocationContainingIgnoreCase(String location);

    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(p.category) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Product> searchProducts(@Param("query") String query);

    @Query("SELECT DISTINCT p.category FROM Product p ORDER BY p.category")
    List<String> findAllCategories();

    @Query("SELECT DISTINCT p.location FROM Product p ORDER BY p.location")
    List<String> findAllLocations();
}

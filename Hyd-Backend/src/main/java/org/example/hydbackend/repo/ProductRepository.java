package org.example.hydbackend.repo;

import org.example.hydbackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findTop11ByOrderByIdAsc();
    List<Product> findTop11ByIdGreaterThanOrderByIdAsc(Long cursor);

    List<Product> findAllByCategoryIgnoreCase(String category);

    @Query("""
    SELECT p FROM Product p WHERE 
    (:keyword IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%',:keyword,'%')))
    AND
    (:category IS NULL OR LOWER(p.category)=LOWER(:category))
""")
    List<Product> searchProducts(
            @Param("keyword") String keyword,
            @Param("category") String category
    );
}

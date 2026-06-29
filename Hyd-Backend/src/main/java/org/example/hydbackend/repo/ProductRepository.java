package org.example.hydbackend.repo;

import org.example.hydbackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findTop11ByOrderByIdAsc();
    List<Product> findTop11ByIdGreaterThanOrderByIdAsc(Long cursor);

}

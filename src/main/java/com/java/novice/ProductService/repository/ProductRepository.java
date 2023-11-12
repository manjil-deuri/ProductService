package com.java.novice.ProductService.repository;

import com.java.novice.ProductService.entity.Product;
import com.java.novice.ProductService.model.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findById(long id);
    List<Product> findAll();
    Product save(Product product);
}

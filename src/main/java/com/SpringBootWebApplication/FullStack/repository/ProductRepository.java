package com.SpringBootWebApplication.FullStack.repository;

import com.SpringBootWebApplication.FullStack.entity.Category;
import com.SpringBootWebApplication.FullStack.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    List<Product> findByCategory(Category category);

}

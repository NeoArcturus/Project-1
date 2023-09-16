package com.SpringBootWebApplication.FullStack.repository;

import com.SpringBootWebApplication.FullStack.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryById(Long id);

    Category findByName(String name);

    void findProductById(Long productId);
}

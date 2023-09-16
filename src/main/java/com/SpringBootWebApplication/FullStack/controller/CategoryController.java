package com.SpringBootWebApplication.FullStack.controller;

import com.SpringBootWebApplication.FullStack.entity.Category;
import com.SpringBootWebApplication.FullStack.exception.CategoryExistsException;
import com.SpringBootWebApplication.FullStack.exception.CategoryNotFoundException;
import com.SpringBootWebApplication.FullStack.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/categories")
    public List<Category> showCategories() {
        return service.getCategories();
    }

    @GetMapping("/category/{id}")
    public Category showCategoryById(@PathVariable Long id) throws CategoryNotFoundException {
        return service.getCategoryById(id);
    }

    @GetMapping("/category/{name}")
    public Category showCategoryByName(@PathVariable String name) throws CategoryNotFoundException {
        return service.getCategoryByName(name);
    }

    @PostMapping("/newCategory")
    public Category addCategory(@RequestBody Category category) throws CategoryExistsException, CategoryNotFoundException {
        return service.addCategory(category);
    }

}

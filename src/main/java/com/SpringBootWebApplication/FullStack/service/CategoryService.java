package com.SpringBootWebApplication.FullStack.service;

import com.SpringBootWebApplication.FullStack.entity.Category;
import com.SpringBootWebApplication.FullStack.entity.Product;
import com.SpringBootWebApplication.FullStack.exception.CategoryExistsException;
import com.SpringBootWebApplication.FullStack.exception.CategoryNotFoundException;
import com.SpringBootWebApplication.FullStack.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> getCategories() {
        return repository.findAll();
    }

    public Category getCategoryById(Long id) throws CategoryNotFoundException {
        Category category = repository.findCategoryById(id);
        if (category == null)
            throw new CategoryNotFoundException("Category not found!", new Exception());
        return category;
    }

    public Category getCategoryByName(String name) throws CategoryNotFoundException {
        Category category = repository.findByName(name);
        if (category == null)
            throw new CategoryNotFoundException("Category not found: " + name + "!", new Exception());
        return category;
    }

    public Category addCategory(Category category) throws CategoryExistsException {
        String name = category.getName();
        if (repository.findByName(name) != null)
            throw new CategoryExistsException("Category of the name " + name + " already exists!", new Exception());
        return repository.save(category);
    }

    public String deleteCategoryById(long id) throws CategoryNotFoundException {
        if (repository.findCategoryById(id) == null)
            throw new CategoryNotFoundException("Category does not exist", new Exception());
        repository.deleteById(id);
        return "Category removed!";
    }
}

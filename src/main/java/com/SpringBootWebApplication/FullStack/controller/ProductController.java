package com.SpringBootWebApplication.FullStack.controller;

import com.SpringBootWebApplication.FullStack.Communication.NewProductRequest;
import com.SpringBootWebApplication.FullStack.entity.Product;
import com.SpringBootWebApplication.FullStack.exception.CategoryNotFoundException;
import com.SpringBootWebApplication.FullStack.exception.ProductNotFoundException;
import com.SpringBootWebApplication.FullStack.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;


    @GetMapping("/products")
    public List<Product> showProducts() {
        return service.getProducts();
    }

    @GetMapping("/productCategory/{id}/products")
    public List<Product> showProductsByCategory(@PathVariable Long id) throws ProductNotFoundException, CategoryNotFoundException {
        return service.getProductsByCategory(id);
    }

    @GetMapping("/product/{name}")
    public Product showProductByName(@PathVariable String name) throws ProductNotFoundException {
        return service.getProductByName(name);
    }

    @PostMapping("/newProduct")
    public Product addProduct(@RequestBody NewProductRequest productRequest) throws CategoryNotFoundException {
        return service.addProduct(productRequest);
    }
}

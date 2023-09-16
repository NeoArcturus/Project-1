package com.SpringBootWebApplication.FullStack.service;

import com.SpringBootWebApplication.FullStack.Communication.NewProductRequest;
import com.SpringBootWebApplication.FullStack.entity.Category;
import com.SpringBootWebApplication.FullStack.entity.Product;
import com.SpringBootWebApplication.FullStack.exception.CategoryNotFoundException;
import com.SpringBootWebApplication.FullStack.exception.ProductNotFoundException;
import com.SpringBootWebApplication.FullStack.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryService service;

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductByName(String name) throws ProductNotFoundException {
        Product product = repository.findByName(name);
        if (product == null)
            throw new ProductNotFoundException("This product does not exist!", new Exception());
        return product;
    }

    public List<Product> getProductByCategory(Long id) throws CategoryNotFoundException {
        Category category = service.getCategoryById(id);
        return repository.findByCategory(category);
    }

    public Product addProduct(NewProductRequest productRequest) throws CategoryNotFoundException {
        Category category = service.getCategoryByName(productRequest.getCategory());
        Product product = new Product();
        product.setCategory(category);
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setImage(productRequest.getImage());
        return repository.save(product);
    }

    public List<Product> getProductsByCategory(Long id) throws CategoryNotFoundException, ProductNotFoundException {
        Category category = service.getCategoryById(id);
        List<Product> productList = repository.findByCategory(category);
        if (productList.isEmpty())
            throw new ProductNotFoundException("Cannot find products from the category: " + category.getName(), new Exception());
        return productList;
    }

    public Category getCategoryByProductName(Product prd) throws CategoryNotFoundException {
        Product product = repository.findByName(prd.getName());
        Category category = product.getCategory();
        if (category == null)
            throw new CategoryNotFoundException("Category not found!", new Exception());
        return category;
    }

    public String getProductImage(Product prd) throws ProductNotFoundException {
        Product product = repository.findByName(prd.getName());
        if (product == null)
            throw new ProductNotFoundException("This product does not exist!", new Exception());
        return product.getImage();
    }

    public void updateProductFromTransaction(Product product) {
        repository.save(product);
    }
}

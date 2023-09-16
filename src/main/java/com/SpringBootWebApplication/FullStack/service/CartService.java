package com.SpringBootWebApplication.FullStack.service;

import com.SpringBootWebApplication.FullStack.Communication.CartCreation;
import com.SpringBootWebApplication.FullStack.Communication.AddProductRequest;
import com.SpringBootWebApplication.FullStack.Communication.RemoveProductRequest;
import com.SpringBootWebApplication.FullStack.entity.Cart;
import com.SpringBootWebApplication.FullStack.entity.Product;
import com.SpringBootWebApplication.FullStack.exception.*;
import com.SpringBootWebApplication.FullStack.repository.CartRepository;
import com.SpringBootWebApplication.FullStack.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;


    public Cart addToCart(AddProductRequest request) throws ProductExistInTheCartException {
        Cart cart = repository.findCartById(request.getCartId());

        Product product = productRepository.findByName(request.getProductName());

        List<Product> productList = cart.getProductList();
        for (Product i : productList) {
            if (i.getName().equals(request.getProductName()))
                throw new ProductExistInTheCartException("Product is already present!", new Exception());
        }
        productList.add(product);
        cart.setProductList(productList);

        List<Long> productQuantity = cart.getProductQuantity();
        productQuantity.add(request.getProductQuantity());
        cart.setProductQuantity(productQuantity);

        return repository.save(cart);
    }

    public Cart createCart(CartCreation cartCreation) throws UserNotFoundException {
        Cart cart = new Cart();
        cart.setUser(userService.getUserById(cartCreation.getUserId()));
        cart.setProductList(new ArrayList<>());
        cart.setProductQuantity(new ArrayList<>());
        return repository.save(cart);
    }

    public Cart removeFromCart(RemoveProductRequest request) throws ProductNotFoundException {
        Cart cart = repository.findCartById(request.getCartId());
        List<Product> productList = cart.getProductList();
        List<Long> quantityList = cart.getProductQuantity();
        boolean flag = false;
        for (Product product : productList) {
            if (product.getName().equals(request.getProductName())) {
                quantityList.remove(quantityList.get(productList.indexOf(product)));
                productList.remove(product);
                flag = true;
                break;
            }
        }
        if (!flag)
            throw new ProductNotFoundException("Product does not exist in the cart", new Exception());
        cart.setProductList(productList);
        return repository.save(cart);
    }

    public Cart showCartByUserId(Long id) throws CartNotFoundException {
        Cart cart = repository.findByUserId(id);
        if (cart == null)
            throw new CartNotFoundException("Cart does not exist!", new Exception());
        return cart;
    }

    public Cart updateProduct(AddProductRequest request) throws ProductNotFoundException {
        Cart cart = repository.findCartById(request.getCartId());

        List<Product> productList = cart.getProductList();
        List<Long> quantityList = cart.getProductQuantity();

        int pos = 0;
        boolean flag = false;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getName().equals(request.getProductName())) {
                pos = i;
                flag = true;
                break;
            }
        }
        if (!flag)
            throw new ProductNotFoundException("Product does not exist in the cart", new Exception());
        quantityList.remove(quantityList.get(pos));
        quantityList.add(request.getProductQuantity());
        cart.setProductQuantity(quantityList);
        return cart;
    }

    public Product getProductByName(String name, long id) throws ProductNotFoundException {
        Cart cart = repository.findCartById(id);
        List<Product> productList = cart.getProductList();
        Product product = null;

        for (Product i : productList) {
            if (i.getName().equals(name)) {
                product = i;
                break;
            }
        }
        if (product == null)
            throw new ProductNotFoundException("This product does not exist in your cart", new Exception());
        return product;
    }

    public Long getProductQuantityByName(String name, long id) throws ProductNotFoundException {
        Cart cart = repository.findCartById(id);
        List<Product> productList = cart.getProductList();
        Product product = null;
        List<Long> quantity = cart.getProductQuantity();
        long productQuantity = 0;

        for (Product i : productList) {
            if (i.getName().equals(name)) {
                product = i;
                productQuantity = quantity.get(productList.indexOf(i));
                break;
            }
        }
        if (product == null)
            throw new ProductNotFoundException("This product does not exist in your cart", new Exception());
        return productQuantity;
    }
}

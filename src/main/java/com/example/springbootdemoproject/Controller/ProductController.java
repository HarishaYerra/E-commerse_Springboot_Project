package com.example.springbootdemoproject.Controller;


import com.example.springbootdemoproject.Model.Product;
import com.example.springbootdemoproject.Model.User;
import com.example.springbootdemoproject.Service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //get all users
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    //create user
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveproduct(product);
    }

    //get user by ID
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    //Update User
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    //Delete User
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "User deleted Successfully";
    }
}


package com.example.springbootdemoproject.Service;
import com.example.springbootdemoproject.Model.Product;
import com.example.springbootdemoproject.Model.User;
import com.example.springbootdemoproject.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private Product updatedProduct;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //get all products
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    //creating product
    public Product saveproduct(Product product) {
        System.out.println("Saving Product: " + product.getName() + " Price: " + product.getPrice());
        return productRepository.save(product);
    }

    //get product by ID
    public Optional<Product> getProductById(Long id) {

        return productRepository.findById(id);
    }


    //Update product
    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(Product -> {
            Product.setName(updatedProduct.getName());
            Product.setPrice(updatedProduct.getPrice());
            return productRepository.save(Product);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    //Delete Product
    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }
}

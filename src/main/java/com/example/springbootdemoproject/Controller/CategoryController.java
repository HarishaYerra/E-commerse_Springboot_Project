package com.example.springbootdemoproject.Controller;

import com.example.springbootdemoproject.Model.Category;
import com.example.springbootdemoproject.Model.CustomerOrder;
import com.example.springbootdemoproject.Model.Product;
import com.example.springbootdemoproject.Model.User;
import com.example.springbootdemoproject.Repository.CustomerOrderRepository;
import com.example.springbootdemoproject.Repository.UserRepository;
import com.example.springbootdemoproject.Service.CategoryService;
import com.example.springbootdemoproject.Service.OrderService;
import com.example.springbootdemoproject.Service.ProductService;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //get all users
    @GetMapping
    public List<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }

    //create user
    @PostMapping
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    //get user by ID
    @GetMapping("/{id}")
    public Optional<Category> getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    //Update User
    @PutMapping("/{id}")
    public Category updatecategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        return categoryService.updatecategory(id, updatedCategory);
    }

    //Delete User
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return "User deleted Successfully";
    }
}


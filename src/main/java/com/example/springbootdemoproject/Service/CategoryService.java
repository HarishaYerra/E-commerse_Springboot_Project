package com.example.springbootdemoproject.Service;
import com.example.springbootdemoproject.Model.Category;
import com.example.springbootdemoproject.Model.CustomerOrder;
import com.example.springbootdemoproject.Model.Product;
import com.example.springbootdemoproject.Model.User;
import com.example.springbootdemoproject.Repository.CategoryRepository;
import com.example.springbootdemoproject.Repository.CustomerOrderRepository;
import com.example.springbootdemoproject.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    //private Order updatedOrder;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //get all category
    public List<Category> getAllCategory() {

        return categoryRepository.findAll();
    }

    //creating category
    public Category saveCategory(Category category) {
        if(category.getProducts()!=null){
            for(Product product:category.getProducts()){
                product.setCategory(category);
            }
        }

        return categoryRepository.save(category);
    }

    //get category by ID
    public Optional<Category> getCategoryById(Long id) {

        return categoryRepository.findById(id);
    }

    // Fetch all categories with their order
    @Transactional(readOnly = true)
    public List<Category> getAllCategoriesWithProducts() {
        return categoryRepository.findAll(); // Now includes products
    }

    //Update order
    public Category updatecategory(Long id, Category updatedCategory) {
        return categoryRepository.findById(id).map(Category -> {
            Category.setName(updatedCategory.getName());
            return categoryRepository.save(Category);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    //Delete order
    public void deleteCategory(Long id) {

        categoryRepository.deleteById(id);
    }
}

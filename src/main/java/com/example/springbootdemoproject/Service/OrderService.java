package com.example.springbootdemoproject.Service;
import com.example.springbootdemoproject.Model.Category;
import com.example.springbootdemoproject.Model.CustomerOrder;
import com.example.springbootdemoproject.Model.Product;
import com.example.springbootdemoproject.Model.User;
import com.example.springbootdemoproject.Repository.CustomerOrderRepository;
import com.example.springbootdemoproject.Repository.ProductRepository;
import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    private final CustomerOrderRepository customerOrderRepository;
    private Order updatedOrder;
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    public OrderService(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    //get all orders
    public List<CustomerOrder> getAllOrders() {

        return customerOrderRepository.findAll();
    }

    //creating orders
    public CustomerOrder saveorder(CustomerOrder order) {
        if(order.getUser() == null) {
            throw new RuntimeException("User not found");
        }
        if(order.getCategories()!=null){
            log.info("Saving order with ID: {}", order.getId());
            for(Category category :order.getCategories()){
                if(category.getProducts()!=null){
                    for(Product product : category.getProducts()){
                        log.info("Category: {} -> Order: {}", category.getName(), category.getOrder().getId());
                        product.setCategory(category);
                    }
                }
                category.setOrder(order);
            }
        }
        return customerOrderRepository.save(order);
    }

    //get order by ID
    public Optional<CustomerOrder> getOrderById(Long id) {

        return customerOrderRepository.findById(id);
    }

    @Transactional
    public Optional<CustomerOrder> getOrderByIdWithCategoriesAndProducts(Long id){
        Optional<CustomerOrder> order = customerOrderRepository.findByIdWithCategoriesAndProducts(id);

        // Force Hibernate to load products
// Unwrap Optional before calling getCategories()
        order.ifPresent(o -> {
            for (Category category : o.getCategories()) {
                category.getProducts().size(); // Force Hibernate to load products
            }
        });
        return order;
    }
    //Update order
    public CustomerOrder updateOrder(Long id, CustomerOrder updatedOrder) {
        return customerOrderRepository.findById(id).map(order -> {
            order.setName(updatedOrder.getName());
            return customerOrderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    //Delete order
    public void deleteOrder(Long id) {

        customerOrderRepository.deleteById(id);
    }
}

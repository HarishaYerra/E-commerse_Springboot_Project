package com.example.springbootdemoproject.Controller;

import com.example.springbootdemoproject.Model.Category;
import com.example.springbootdemoproject.Model.CustomerOrder;
import com.example.springbootdemoproject.Model.Product;
import com.example.springbootdemoproject.Model.User;
import com.example.springbootdemoproject.Repository.CustomerOrderRepository;
import com.example.springbootdemoproject.Repository.UserRepository;
import com.example.springbootdemoproject.Service.OrderService;
import com.example.springbootdemoproject.Service.ProductService;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //get all users
    @GetMapping
    public List<CustomerOrder> getAllOrders() {
        return orderService.getAllOrders();
    }

    //create user
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CustomerOrder createorder(@RequestBody CustomerOrder order) {
        return orderService.saveorder(order);
    }

    //get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerOrder> getOrderById(@PathVariable Long id){
       // return orderService.getOrderById(id);
        Optional<CustomerOrder> orderOptional = orderService.getOrderById(id);

        if (orderOptional.isPresent()) {
            return ResponseEntity.ok(orderOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }

        //return ResponseEntity.ok(order);
    }

    //Update User
    @PutMapping("/{id}")
    public CustomerOrder updateOrder(@PathVariable Long id, @RequestBody CustomerOrder updatedOrder) {
        return orderService.updateOrder(id, updatedOrder);
    }

    //Delete User
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return "User deleted Successfully";
    }
}


package com.example.springbootdemoproject.Controller;


import com.example.springbootdemoproject.Model.User;
import com.example.springbootdemoproject.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //create user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveuser(user);
    }

    //get user by ID
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    //Update User
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    //Delete User
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "User deleted Successfully";
    }
}


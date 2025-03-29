package com.example.springbootdemoproject.Service;
import com.example.springbootdemoproject.Model.User;
import com.example.springbootdemoproject.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private User updatedUser;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //get all users
    public List<User> getAllUsers(){

        return userRepository.findAll();
    }

    //creating user
    public User saveuser(User user){
        return userRepository.save(user);
    }

    //get user by ID
    public Optional<User> getUserById(Long id){

        return userRepository.findById(id);
    }


    //Update User
    public User updateUser(Long id,User updatedUser){
        return userRepository.findById(id).map(user->{
            user.setName(updatedUser.getName());
            return userRepository.save(user);
        }).orElseThrow(()-> new RuntimeException("User not found"));
    }

    //Delete User
    public void deleteUser(Long id){

        userRepository.deleteById(id);
    }
}

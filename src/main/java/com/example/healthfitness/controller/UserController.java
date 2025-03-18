package com.example.healthfitness.controller;


import com.example.healthfitness.dto.request.UserCreationRequest;
import com.example.healthfitness.dto.request.UserUpdateRequest;
import com.example.healthfitness.entity.User;
import com.example.healthfitness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") // Cho phép React gọi API
@RestController
@RequestMapping("/Users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    User createUser(@RequestBody UserCreationRequest request) {

       return userService.createUser(request);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")  // Đổi UserId -> userId
    public User getUser(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }
    @PutMapping("/{userId}")
    User updateUser(@RequestBody UserUpdateRequest Request, @PathVariable("userId") String userId) {


        return  userService.updateUser(userId, Request);
    }

    @DeleteMapping("/{userId}")


    String deleteUser(@PathVariable("userId") String userId) {


    userService.deleteUser(userId);
    return "User has been deleted";


    }



}






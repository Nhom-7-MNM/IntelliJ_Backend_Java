package com.example.healthfitness.service;


import com.example.healthfitness.dto.request.UserCreationRequest;
import com.example.healthfitness.dto.request.UserUpdateRequest;
import com.example.healthfitness.entity.User;
import com.example.healthfitness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest Request){

        User user = new User();

        user.setFullName(Request.getFullName());
        user.setPhone(Request.getPhone());
        user.setEmail(Request.getEmail());
        user.setPassword(Request.getPassword());
        user.setRole(Request.getRole());
        user.setCreatedAt(Request.getCreatedAt());
        user.setUpdatedAt(Request.getUpdatedAt());
        return userRepository.save(user);
    }
        public List<User> getUsers() {
             return (List<User>) userRepository.findAll();

}
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User updateUser(String userId ,UserUpdateRequest Request){

        User user = getUserById(userId);

        user.setPhone(Request.getPhone());
        user.setEmail(Request.getEmail());
        user.setPassword(Request.getPassword());
        user.setRole(Request.getRole());
        user.setCreatedAt(Request.getCreatedAt());
        user.setUpdatedAt(Request.getUpdatedAt());

        return userRepository.save(user);
    }

    public void deleteUser(String userId) {

        userRepository.deleteById(userId);
    }
}

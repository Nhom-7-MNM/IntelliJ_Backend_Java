package com.example.healthfitness.service;


import com.example.healthfitness.dto.request.UserCreationRequest;
import com.example.healthfitness.dto.request.UserUpdateRequest;
import com.example.healthfitness.entity.User;
import com.example.healthfitness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request) {
        // Kiểm tra email đã tồn tại hay chưa
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email đã tồn tại"); // ✅ Trả về HTTP 400 thay vì 500
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setCreatedAt(request.getCreatedAt());
        user.setUpdatedAt(request.getUpdatedAt());


            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder( 10 );
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user); // ✅ Đã có userRepository
    }
        public List<User> getUsers() {
             return (List<User>) userRepository.findAll();

}
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found  "));
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

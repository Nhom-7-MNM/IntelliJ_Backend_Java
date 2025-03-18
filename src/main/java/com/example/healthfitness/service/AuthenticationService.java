package com.example.healthfitness.service;


import com.example.healthfitness.dto.request.AuthenticationRequest;
import com.example.healthfitness.entity.User;
import com.example.healthfitness.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    final UserRepository userRepository;
    final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean authenticated(AuthenticationRequest authenticationRequest) {
        User user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User Exit"));

        return passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
    }

}

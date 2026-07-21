package org.example.hydbackend.auth.service;

import org.example.hydbackend.auth.entity.Role;
import org.example.hydbackend.auth.entity.User;
import org.example.hydbackend.auth.dto.RegisterRequest;
import org.example.hydbackend.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public void register(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        User user=new User();
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        String encodedPassword=passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.USER);
        userRepository.save(user);
    }


}

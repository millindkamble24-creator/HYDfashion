package org.example.hydbackend.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.hydbackend.auth.dto.RegisterRequest;
import org.example.hydbackend.auth.service.AuthService;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    public final AuthService authService;

    public AuthController(AuthService authService){
        this.authService=authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request){
        authService.register(request);
        return "User Registered Successfully";
    }
}

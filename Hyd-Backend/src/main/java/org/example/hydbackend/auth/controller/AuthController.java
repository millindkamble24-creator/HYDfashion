package org.example.hydbackend.auth.controller;

import org.springframework.web.bind.annotation.*;
import org.example.hydbackend.auth.dto.RegisterRequest;
import org.example.hydbackend.auth.service.AuthService;
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/auth")
public class AuthController {
    public final AuthService authService;

    public AuthController(AuthService authService){
        this.authService=authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request){
        System.out.println("REGISTER API HIT");
        authService.register(request);
        return "User Registered Successfully";
    }
}

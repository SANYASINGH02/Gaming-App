package com.example.aagApp.controller;


import com.example.aagApp.entity.Users;
import com.example.aagApp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService service;


    @GetMapping("/home")
    public String home()
    {
        return "Welcome ! ";
    }
    @PostMapping("/signup")
    public String signup(@RequestBody Users user) {
        return service.signup(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user) {
        return service.verify(user);
    }

}

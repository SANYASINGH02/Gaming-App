
package com.example.aagApp.services;

import com.example.aagApp.component.JWTService;
import com.example.aagApp.entity.Users;
import com.example.aagApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepo repo;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String signup(Users user) {
        Users isAlready = repo.findByUsername(user.getUsername());
        if(isAlready != null)
            return "Username already exists.";
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return "User Registered Successfully";
    }

    public ResponseEntity<String> verify(Users user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(user.getUsername());
            return ResponseEntity.ok(token);
        }
        throw new BadCredentialsException("Invalid credentials");
    }
}

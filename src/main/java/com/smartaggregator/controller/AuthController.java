package com.smartaggregator.controller;

import com.smartaggregator.security.JwtUtil;
import com.smartaggregator.dto.AuthRequest;
import com.smartaggregator.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        //In real apps, validate with DB or LDAP
        if ("zain".equals(request.getUsername()) && "supersecret".equals(request.getPassword())) {
            String token = jwtUtil.generateToken(request.getUsername());//assuming that this will generate a token base64 encoded given the usernmae passed in the request
            return new AuthResponse(token);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}

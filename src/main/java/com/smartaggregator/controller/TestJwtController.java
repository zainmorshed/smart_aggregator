package com.smartaggregator.controller;

import com.smartaggregator.security.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestJwtController {

    private final JwtUtil jwtUtil;

    public TestJwtController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // Example: GET /generate-token?username=zain
    @GetMapping("/generate-token")
    public String generateToken(@RequestParam String username) {
        return jwtUtil.generateToken(username);
    }
}

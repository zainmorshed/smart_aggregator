package com.smartaggregator.controller;

import com.smartaggregator.dto.UserRequestDTO;
import com.smartaggregator.service.UserService;
import com.smartaggregator.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User addUser(@RequestBody UserRequestDTO request) {
        userService.addUser(request.getUsername(), request.getStocks(), request.getCryptos());
        //add the external api call later and  error handling
        return userService.getAllUsers().stream()
            .filter(u -> u.getName().equals(request.getUsername()))
            .findFirst()
            .orElse(null);
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
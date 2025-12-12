package com.smartaggregator.controller;

import com.smartaggregator.dto.SummaryResponse;
import com.smartaggregator.dto.UserRequestDTO;
import com.smartaggregator.service.AggregatorService;
import com.smartaggregator.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.smartaggregator.entity.User;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AggregatorService aggregatorService;

    @PostMapping("/add")
    public SummaryResponse addUserAndGetSummary(@RequestBody UserRequestDTO request) {
        userService.addUser(request.getUsername(), request.getStocks(), request.getCryptos());
        return aggregatorService.getSummaryForUser(request.getUsername());
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();

    }
}

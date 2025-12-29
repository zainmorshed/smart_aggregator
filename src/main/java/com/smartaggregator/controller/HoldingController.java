package com.smartaggregator.controller;

import com.smartaggregator.entity.UserHolding;
import com.smartaggregator.repository.UserHoldingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/holdings")
public class HoldingController {
    
    @Autowired
    private UserHoldingRepository holdingRepository;
    
    @PostMapping
    public UserHolding addHolding(@RequestBody UserHolding holding) {
        return holdingRepository.save(holding);
    }
    
    @GetMapping
    public List<UserHolding> getUserHoldings(@RequestParam String username) {
        return holdingRepository.findAll().stream()
            .filter(h -> h.getUsername().equals(username))
            .toList();
    }
}
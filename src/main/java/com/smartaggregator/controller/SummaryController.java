package com.smartaggregator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartaggregator.service.AggregatorService;
import com.smartaggregator.dto.SummaryResponse;

@RestController
@RequestMapping("/summary")
public class SummaryController {
    @Autowired
    private AggregatorService aggregatorService;

    @GetMapping
    public SummaryResponse getUserSummary(@RequestParam String user) {
        return aggregatorService.getSummaryForUser(user);
    }
}

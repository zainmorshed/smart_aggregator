package com.smartaggregator.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.math.BigDecimal;
import com.smartaggregator.service.PortfolioService;
import com.smartaggregator.dto.AddStockRequest;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PostMapping("/{userId}/stocks")
    public void addStock(
            @PathVariable String userId,
            @RequestBody AddStockRequest request
    ) {
        portfolioService.addStock(userId, request);
    }

    @GetMapping("/{userId}/net-worth")
    public BigDecimal getNetWorth(@PathVariable String userId) {
        return portfolioService.calculateNetWorth(userId);
    }
}

package com.smartaggregator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.smartaggregator.dto.StockPriceDTO;
import com.smartaggregator.service.StockService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
public class StockController {
    @Autowired
    private StockService stockService;
    @GetMapping("/price")
    public StockPriceDTO getPrice(@RequestParam String symbol) {
        return stockService.getStockPrice(symbol);
    }
}

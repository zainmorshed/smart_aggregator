package com.smartaggregator.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import org.springframework.beans.factory.annotation.Value;

import com.smartaggregator.dto.StockPriceDTO;
import com.smartaggregator.entity.Stock;

import org.json.JSONObject;

import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;


@Service
public class StockService {

    @Value("${twelvedata.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Stock> getStockData(List<String> symbols) {
    List<Stock> stockList = new ArrayList<>();

    for (String symbol : symbols) {
        try {
            double price = getStockPrice(symbol).getPrice(); // from your existing method

            // For now, using dummy values for name, change24h, and trend
            Stock stock = new Stock(
                symbol.toUpperCase(),
                "Company " + symbol.toUpperCase(), // Dummy name
                price,
                0.0, // change24h (use 0.0 for now)
                "neutral" // trend (calculate later if needed)
            );

            stockList.add(stock);
        } catch (Exception e) {
            System.out.println("Failed to fetch stock: " + symbol + " - " + e.getMessage());
        }
    }

    return stockList;
}

    public StockPriceDTO getStockPrice(String symbol) {
        String url = UriComponentsBuilder
            .fromHttpUrl("https://api.twelvedata.com/price")
            .queryParam("symbol", symbol)
            .queryParam("apikey", apiKey)
            .toUriString();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "Mozilla/5.0");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
            );

            JSONObject json = new JSONObject(response.getBody());

            if (json.has("code")) {
                throw new RuntimeException("Error from API: " + json.getString("message"));
            }

            double price = json.getDouble("price");
            return new StockPriceDTO(symbol.toUpperCase(), price);

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch stock price: " + e.getMessage());
        }
    }
}


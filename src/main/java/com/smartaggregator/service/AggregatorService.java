package com.smartaggregator.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smartaggregator.dto.SummaryResponse;
import com.smartaggregator.entity.Stock;
import com.smartaggregator.entity.Crypto;
import com.smartaggregator.entity.NewsArticle;
import com.smartaggregator.entity.UserHolding;
import com.smartaggregator.repository.UserHoldingRepository;
import java.time.Instant;
import java.util.ArrayList;

@Service
public class AggregatorService {
    @Autowired
    private UserHoldingRepository holdingRepository;

    public SummaryResponse getSummaryForUser(String username){
        // Get user's stock holdings
        List<UserHolding> stockHoldings = holdingRepository.findByUsernameAndType(username, "STOCK");
        
        // Convert to Stock entities with dummy prices for now
        List<Stock> stockData = stockHoldings.stream().map(h -> {
            Stock stock = new Stock();
            stock.setSymbol(h.getSymbol());
            stock.setName("Stock " + h.getSymbol());
            stock.setPrice(150.0); // Dummy price
            stock.setQuantity(h.getQuantity());
            stock.setChange24h(0.0);
            stock.setTrend("neutral");
            return stock;
        }).collect(Collectors.toList());
        
        // Get user's crypto holdings
        List<UserHolding> cryptoHoldings = holdingRepository.findByUsernameAndType(username, "CRYPTO");
        
        // Convert to Crypto entities
        List<Crypto> cryptoData = cryptoHoldings.stream().map(h -> {
            Crypto crypto = new Crypto();
            crypto.setSymbol(h.getSymbol());
            crypto.setName("Crypto " + h.getSymbol());
            crypto.setPrice(50000.0); // Dummy price
            crypto.setQuantity(h.getQuantity());
            crypto.setChange24h(0.0);
            crypto.setTrend("neutral");
            return crypto;
        }).collect(Collectors.toList());
        
        List<NewsArticle> news = new ArrayList<>();

        return new SummaryResponse(username, Instant.now().toString(), stockData, cryptoData, news);
    }
}
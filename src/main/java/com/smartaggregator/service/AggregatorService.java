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
import com.smartaggregator.service.StockService;
import com.smartaggregator.service.CryptoService;
import java.time.Instant;
import java.util.ArrayList;

@Service
public class AggregatorService {
    @Autowired
    private UserHoldingRepository holdingRepository;
    @Autowired
    private StockService stockService;
    @Autowired
    private CryptoService cryptoService;

    public SummaryResponse getSummaryForUser(String username){
        // Get user's stock holdings
        List<UserHolding> stockHoldings = holdingRepository.findByUsernameAndType(username, "STOCK");
        
        // Fetch real prices and convert to Stock entities
        List<Stock> stockData = stockHoldings.stream().map(h -> {
            Stock stock = new Stock();
            stock.setSymbol(h.getSymbol());
            stock.setName("Stock " + h.getSymbol());
            
            try {
                //get real-time price from TwelveData
                double price = stockService.getStockPrice(h.getSymbol()).getPrice();
                stock.setPrice(price);
            } catch (Exception e) {
                System.err.println("Failed to fetch price for " + h.getSymbol() + ", using fallback");
                stock.setPrice(0.0); // Fallback if the API fails
            }
            
            stock.setQuantity(h.getQuantity());
            stock.setChange24h(0.0);
            stock.setTrend("neutral");
            return stock;
        }).collect(Collectors.toList());
        
        //get user's crypto holdings
        List<UserHolding> cryptoHoldings = holdingRepository.findByUsernameAndType(username, "CRYPTO");
        
        //get real crypto prices
        List<Crypto> cryptoData = cryptoHoldings.stream().map(h -> {
            Crypto crypto = new Crypto();
            crypto.setSymbol(h.getSymbol());
            crypto.setName("Crypto " + h.getSymbol());
            
            try {
                //get the real-time price from coinmarketcap
                double price = cryptoService.getCryptoPrice(h.getSymbol()).getPrice();
                crypto.setPrice(price);
            } catch (Exception e) {
                System.err.println("Failed to fetch price for " + h.getSymbol() + ", using fallback");
                crypto.setPrice(0.0); // Fallback if API fails
            }
            
            crypto.setQuantity(h.getQuantity());
            crypto.setChange24h(0.0);
            crypto.setTrend("neutral");
            return crypto;
        }).collect(Collectors.toList());
        
        List<NewsArticle> news = new ArrayList<>();

        return new SummaryResponse(username, Instant.now().toString(), stockData, cryptoData, news);
    }
}